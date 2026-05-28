SUMMARY = "Axiado internal Root of Trust bootflow component"
LICENSE = "CLOSED"

PV = "1.0+dev"

inherit deploy

SRCBRANCH = "release/release-u-0.1.0"

SRCREV_FORMAT = "kw-hal_ax-hal_bali-hal_caliptra-sw"
SRC_URI_KW_HAL = "git://bitbucket.org/ax-engg/kw-hal.git;protocol=https"
SRC_URI_AX_HAL = "git://bitbucket.org/ax-engg/ax_hal.git;protocol=https"
SRC_URI_BAIL_HAL = "gitsm://bitbucket.org/ax-engg/bali.git;protocol=https"
SRC_URI_CALIPTRA_SW = "gitsm://bitbucket.org/ax-engg/caliptra-sw.git;protocol=https"

SRC_URI = "${SRC_URI_KW_HAL};branch=${SRCBRANCH};name=kw-hal \
           ${SRC_URI_AX_HAL};branch=${SRCBRANCH};name=ax-hal;destsuffix=git/ax_hal \
           ${SRC_URI_BAIL_HAL};branch=${SRCBRANCH};name=bali-hal;destsuffix=git/bali_hal \
           ${SRC_URI_CALIPTRA_SW};branch=${SRCBRANCH};name=caliptra-sw;destsuffix=git/caliptra-sw \
           file://0001-freertos_r52_kernel_support.patch;patchdir=${S}/bali_hal/library/FreeRTOS-Kernel \
           file://0002-sysmgr-use-prebuilt-bali-hal-impl.patch;patchdir=${S} \
           file://0003-ax_hal-Add-sysroot-into-the-list-of-headers-path.patch;patchdir=${S}/ax_hal \
           "

SRCREV_kw-hal = "53919ed17e467fca24d72782c3b8c138fec227cf"
SRCREV_ax-hal = "01ceee9cb89c5c0b993b6acfddd7870920bf52c6"
SRCREV_bali-hal = "9f05b544a5470400981b3e3d40e22b076feed9ef"
SRCREV_caliptra-sw = "11a5c54aa192b41fa9ca4e11aa9de78d26425c90"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

DEPENDS = "cmake-native gcc-arm-none-eabi-native kw-hal ax-bsp-headers"

do_configure() {
    mkdir -p ${B}
    cmake -S ${S}/sysmgr -B ${B} \
        -DCORE_NUM=CORE0 \
        -DCMAKE_TOOLCHAIN_FILE=${S}/sysmgr/arm-none-eabi.cmake \
        -DTOOLCHAIN_PATH=${STAGING_DIR_NATIVE}/usr \
        -DRECIPE_SYSROOT_INCLUDE_DIR=${RECIPE_SYSROOT}${includedir}/ax-bsp-headers \
        -DKW_ROOT=${S} \
        -DCMAKE_SYSROOT=${RECIPE_SYSROOT}
}

do_compile() {
    cmake --build ${B}
}

do_install() {
    install -d ${D}${datadir}/ax3005
    install -m 0644 ${B}/sysmgr.bin ${D}${datadir}/ax3005
}

# Ensure to remove the pre-built one
do_deploy[depends] += "bmc-pb:do_deploy"
do_deploy() {
    if [ -e "${DEPLOY_DIR_IMAGE}"/sysmgr.bin ]; then
        rm ${DEPLOY_DIR_IMAGE}/sysmgr.bin
    fi
    install -m 0644 ${B}/sysmgr.bin ${DEPLOYDIR}
}

FILES:${PN} = "${datadir}/ax3005/sysmgr.bin"

addtask do_deploy before do_build after do_install
