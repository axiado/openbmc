SUMMARY = "Axiado internal Root of Trust bootflow component"
LICENSE = "CLOSED"

SRCBRANCH = "release/release-u-0.1.0"

SRCREV_FORMAT = "kw-hal_ax-bsp-headers_ax-hal_bali-hal_caliptra-sw"
SRC_URI_KW_HAL = "git://bitbucket.org/ax-engg/kw-hal.git;protocol=https"
SRC_URI_AX_BSP_HEADERS = "git://bitbucket.org/ax-engg/ax-bsp-headers.git;protocol=https"
SRC_URI_AX_HAL = "git://bitbucket.org/ax-engg/ax_hal.git;protocol=https"
SRC_URI_BAIL_HAL = "gitsm://bitbucket.org/ax-engg/bali.git;protocol=https"
SRC_URI_CALIPTRA_SW = "gitsm://bitbucket.org/ax-engg/caliptra-sw.git;protocol=https"

SRC_URI = "${SRC_URI_KW_HAL};branch=${SRCBRANCH};name=kw-hal \
           ${SRC_URI_AX_BSP_HEADERS};branch=${SRCBRANCH};name=ax-bsp-headers;destsuffix=git/ax-bsp-headers \
           ${SRC_URI_AX_HAL};branch=${SRCBRANCH};name=ax-hal;destsuffix=git/ax_hal \
           ${SRC_URI_BAIL_HAL};branch=${SRCBRANCH};name=bali-hal;destsuffix=git/bali_hal \
           ${SRC_URI_CALIPTRA_SW};branch=${SRCBRANCH};name=caliptra-sw;destsuffix=git/caliptra-sw \
           file://0001-freertos_r52_kernel_support.patch;patchdir=${S}/bali_hal/library/FreeRTOS-Kernel \
           file://0002-sysmgr-use-prebuilt-bali-hal-impl.patch \
           "

SRCREV_kw-hal = "53919ed17e467fca24d72782c3b8c138fec227cf"
SRCREV_ax-bsp-headers = "1736e94ee2e94f2b09665d5a0c3bd8f3b50519a3"
SRCREV_ax-hal = "01ceee9cb89c5c0b993b6acfddd7870920bf52c6"
SRCREV_bali-hal = "9f05b544a5470400981b3e3d40e22b076feed9ef"
SRCREV_caliptra-sw = "11a5c54aa192b41fa9ca4e11aa9de78d26425c90"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

DEPENDS = "cmake-native gcc-arm-none-eabi-native kw-hal"

do_configure() {
    mkdir -p ${B}
    cmake -S ${S}/sysmgr -B ${B} \
        -DCORE_NUM=CORE0 \
        -DCMAKE_TOOLCHAIN_FILE=${S}/sysmgr/arm-none-eabi.cmake \
        -DTOOLCHAIN_PATH=${STAGING_DIR_NATIVE}/usr \
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

FILES:${PN} = "${datadir}/ax3005/sysmgr.bin"
