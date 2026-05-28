require kw-hal.inc

SRCREV_kw-hal = "22c0c4c286346a682eda890c85365fef1e2856b1"
SRCREV_ax-hal = "314c626b6ff3a29268955d4920aa7916dfa69c55"
SRCREV_bali-hal = "1f047ed1b1ae1ac15d0071f69e26663974a59076"
SRCREV_caliptra-sw = "15512531781016c126dcb9ee6963410b6a9200b0"

SRCBRANCH = "develop"

SRC_URI_KW_HAL = "git://git@github.com/axiado/kw-hal;protocol=ssh"
SRC_URI_AX_HAL = "git://git@github.com/axiado/ax_hal;protocol=ssh"
SRC_URI_BAIL_HAL = "gitsm://git@github.com/axiado/bali_hal;protocol=ssh"
SRC_URI_CALIPTRA_SW = "gitsm://github.com/axiado/caliptra-sw.git;protocol=https"

SRC_URI += "file://0001-freertos_r52_kernel_support.patch;patchdir=${S}/bali_hal/library/FreeRTOS-Kernel \
            file://0003-r52-compile-args-limit-ffreestanding-to-c-asm.patch;patchdir=${S} \
            file://0004-ax-ultra-cmake-limit-ffreestanding-to-c-asm.patch;patchdir=${S}/bali_hal \
            file://0005-ax-hal-libcaliptra-pass-cflags.patch;patchdir=${S}/ax_hal \
            "
PV = "1.0+git"
