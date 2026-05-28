require kw-hal.inc

SRCREV_kw-hal = "5382960f2bde2112d4c8c9249067d387e7eb1c9c"
SRCREV_ax-hal = "314c626b6ff3a29268955d4920aa7916dfa69c55"
SRCREV_bali-hal = "26ceb1bba4a218370dc32c349e038fd5441c1ef1"

SRCBRANCH = "develop"

SRC_URI_KW_HAL = "git://git@github.com/axiado/kw-hal;protocol=ssh"
SRC_URI_AX_HAL = "git://git@github.com/axiado/ax_hal;protocol=ssh"
SRC_URI_BAIL_HAL = "gitsm://git@github.com/axiado/bali_hal;protocol=ssh"

SRC_URI += "file://0001-freertos_r52_kernel_support.patch;patchdir=${S}/bali_hal/library/FreeRTOS-Kernel \
            file://0003-r52-compile-args-limit-ffreestanding-to-c-asm.patch;patchdir=${S} \
            file://0004-ax-ultra-cmake-limit-ffreestanding-to-c-asm.patch;patchdir=${S}/bali_hal \
            file://0005-ax-hal-libcaliptra-pass-cflags.patch;patchdir=${S}/ax_hal \
            "
PV = "1.0+git"
