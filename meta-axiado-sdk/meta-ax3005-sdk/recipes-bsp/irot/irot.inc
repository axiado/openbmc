require kw-hal-src.inc
require ax-hal-src.inc
require bali-hal-src.inc
require sysmgr-src.inc

inherit deploy cmake

OECMAKE_GENERATOR = "Unix Makefiles"

DEPENDS = "cmake-native gcc-arm-none-eabi-native ax-bsp-headers libcaliptra"

SRCREV_FORMAT = "kw-hal_ax-hal_bali-hal_sysmgr"

SRC_URI = "${SRC_URI_KW_HAL};name=kw-hal \
           ${SRC_URI_AX_HAL};name=ax-hal;destsuffix=${P}/ax_hal \
           ${SRC_URI_BALI_HAL};name=bali-hal;destsuffix=${P}/bali_hal \
           ${SRC_URI_SYSMGR};name=sysmgr;destsuffix=${P}/sysmgr_ultra \
           file://0001-freertos_r52_kernel_support.patch;patchdir=${S}/bali_hal/library/FreeRTOS-Kernel \
           file://0003-ax_hal-Add-sysroot-into-the-list-of-headers-path.patch;patchdir=${S}/ax_hal \
           file://0004-r52-compile-args-limit-ffreestanding-to-C-and-ASM-on.patch;patchdir=${S} \
           file://0005-ax-ultra-CMakeLists-limit-ffreestanding-to-C-and-ASM.patch;patchdir=${S}/bali_hal \
           file://0007-ax_hal-allow-caliptra-paths-to-be-set-externally.patch;patchdir=${S}/ax_hal \
           file://0008-base-allow-caliptra-paths-to-be-set-and-skip-in-tree.patch;patchdir=${S} \
           "
