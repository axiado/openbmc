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
           ${SRC_URI_AX_HAL};branch=${SRCBRANCH};name=ax-hal;destsuffix=${P}/ax_hal \
           ${SRC_URI_BAIL_HAL};branch=${SRCBRANCH};name=bali-hal;destsuffix=${P}/bali_hal \
           ${SRC_URI_CALIPTRA_SW};branch=${SRCBRANCH};name=caliptra-sw;destsuffix=${P}/caliptra-sw \
           file://0001-freertos_r52_kernel_support.patch;patchdir=${S}/bali_hal/library/FreeRTOS-Kernel \
           file://0002-sysmgr-use-prebuilt-bali-hal-impl.patch;patchdir=${S} \
           file://0003-ax_hal-Add-sysroot-into-the-list-of-headers-path.patch;patchdir=${S}/ax_hal \
           file://0004-sysmgr-Add-missing-ax-hal-header.patch;patchdir=${S} \
           "

SRCREV_kw-hal = "726cf5202a7bbb14fde862dc23bc84047b57e68d"
SRCREV_ax-hal = "4a5bdca9969d3a6a08e7fb7ccf4c48e35cf45bac"
SRCREV_bali-hal = "923405fc370908c87083aca401167248bc7c6e12"
SRCREV_caliptra-sw = "320f75fca34c59de60a022908090f6a277caa63c"

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

do_deploy() {
    install -m 0644 ${B}/sysmgr.bin ${DEPLOYDIR}
}

FILES:${PN} = "${datadir}/ax3005/sysmgr.bin"

addtask do_deploy before do_build after do_install
