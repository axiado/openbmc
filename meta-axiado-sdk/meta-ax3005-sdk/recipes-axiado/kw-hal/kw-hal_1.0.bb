require kw-hal.inc

DEPENDS += "ax-bsp-headers"

SRCREV_FORMAT += "_caliptra-sw"
SRCREV_kw-hal = "726cf5202a7bbb14fde862dc23bc84047b57e68d"
SRCREV_ax-hal = "4a5bdca9969d3a6a08e7fb7ccf4c48e35cf45bac"
SRCREV_bali-hal = "923405fc370908c87083aca401167248bc7c6e12"
SRCREV_caliptra-sw = "320f75fca34c59de60a022908090f6a277caa63c"

SRCBRANCH = "release/release-u-0.1.0"

SRC_URI_KW_HAL = "git://bitbucket.org/ax-engg/kw-hal.git;protocol=https"
SRC_URI_AX_HAL = "git://bitbucket.org/ax-engg/ax_hal.git;protocol=https"
SRC_URI_BAIL_HAL = "gitsm://bitbucket.org/ax-engg/bali.git;protocol=https"
SRC_URI_CALIPTRA_SW = "gitsm://bitbucket.org/ax-engg/caliptra-sw.git;protocol=https"

SRC_URI += "${SRC_URI_CALIPTRA_SW};branch=${SRCBRANCH};name=caliptra-sw;destsuffix=${P}/caliptra-sw \
            file://0001-freertos_r52_kernel_support.patch;patchdir=${S}/bali_hal/library/FreeRTOS-Kernel \
            file://0002-ax_hal-Add-sysroot-into-the-list-of-headers-path.patch;patchdir=${S}/ax_hal \
            file://0003-r52-compile-args-limit-ffreestanding-to-c-asm.patch;patchdir=${S} \
            file://0004-ax-ultra-cmake-limit-ffreestanding-to-c-asm.patch;patchdir=${S}/bali_hal \
            "

do_configure() {
    mkdir -p ${B}
    cmake -S ${S}/bali_hal -B ${B} \
        -DPLATFORM_TYPE=axiado \
        -DTARGET_PLATFORM=ax-ultra \
        -DCORE_NUM=CORE0 \
        -DCMAKE_TOOLCHAIN_FILE=${S}/bali_hal/system/axiado/arm-none-eabi.cmake \
        -DTOOLCHAIN_PATH=${STAGING_DIR_NATIVE}/usr \
        -DRECIPE_SYSROOT_INCLUDE_DIR=${RECIPE_SYSROOT}${includedir}/ax-bsp-headers \
        -DTEST_TYPE_UNIT=OFF \
        -DTEST_TYPE_INTEGRATION=OFF \
        -DTEST_TYPE_SYSTEM=OFF \
        -DTEST_TYPE_PERFORMANCE=OFF \
        -DTEST_TYPE_ALL=OFF
}

PV = "1.0+dev"
