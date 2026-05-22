require kw-hal.inc

SRCREV_FORMAT += "_ax-bsp-headers"
SRCREV_kw-hal = "53919ed17e467fca24d72782c3b8c138fec227cf"
SRCREV_ax-bsp-headers = "1736e94ee2e94f2b09665d5a0c3bd8f3b50519a3"
SRCREV_ax-hal = "01ceee9cb89c5c0b993b6acfddd7870920bf52c6"
SRCREV_bali-hal = "9f05b544a5470400981b3e3d40e22b076feed9ef"
SRCREV_caliptra-sw = "11a5c54aa192b41fa9ca4e11aa9de78d26425c90"

SRCBRANCH = "release/release-u-0.1.0"

SRC_URI_KW_HAL = "git://bitbucket.org/ax-engg/kw-hal.git;protocol=https"
SRC_URI_AX_BSP_HEADERS = "git://bitbucket.org/ax-engg/ax-bsp-headers.git;protocol=https"
SRC_URI_AX_HAL = "git://bitbucket.org/ax-engg/ax_hal.git;protocol=https"
SRC_URI_BAIL_HAL = "gitsm://bitbucket.org/ax-engg/bali.git;protocol=https"
SRC_URI_CALIPTRA_SW = "gitsm://bitbucket.org/ax-engg/caliptra-sw.git;protocol=https"

SRC_URI += "${SRC_URI_AX_BSP_HEADERS};branch=${SRCBRANCH};name=ax-bsp-headers;destsuffix=git/ax-bsp-headers \
            file://0001-freertos_r52_kernel_support.patch;patchdir=${S}/bali_hal/library/FreeRTOS-Kernel \
            file://0003-r52-compile-args-limit-ffreestanding-to-c-asm.patch;patchdir=${S} \
            file://0004-ax-ultra-cmake-limit-ffreestanding-to-c-asm.patch;patchdir=${S} \
            "
PV = "1.0"
