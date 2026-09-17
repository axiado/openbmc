SUMMARY = "Axiado internal Root of Trust bootflow component"
LICENSE = "CLOSED"

PV = "1.0+dev"

require kw-hal-src.inc
require ax-hal-src.inc
require bali-hal-src.inc
require sysmgr-src.inc

inherit deploy cmake

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

DEPENDS = "cmake-native gcc-arm-none-eabi-native ax-bsp-headers libcaliptra"

OECMAKE_SOURCEPATH = "${S}/sysmgr_ultra"

OECMAKE_GENERATOR = "Unix Makefiles"

# This is a Cortex-R52 baremetal firmware build, so it must use the project's
# own arm-none-eabi toolchain file instead of cmake.bbclass' generated OE
# (aarch64/Linux) one. EXTRA_OECMAKE is appended after OECMAKE_ARGS on the
# cmake command line, so this -DCMAKE_TOOLCHAIN_FILE overrides the class'
# ${WORKDIR}/toolchain.cmake. arm-none-eabi.cmake sets the R52 compilers and
# CMAKE_SYSTEM_NAME=Generic before project(), which the CMakeLists requires.
EXTRA_OECMAKE = "\
    -DCMAKE_TOOLCHAIN_FILE=${S}/sysmgr_ultra/arm-none-eabi.cmake \
    -DTOOLCHAIN_PATH=${STAGING_DIR_NATIVE}/usr \
    -DCMAKE_SYSROOT=${RECIPE_SYSROOT} \
    -DRECIPE_SYSROOT_INCLUDE_DIR=${RECIPE_SYSROOT}${includedir}/ax-bsp-headers \
    -DKW_ROOT=${S} \
    -DCORE_NUM=CORE0 \
    -DENABLE_OPTEE=ON \
    -DBUILD_LIBCALIPTRA=OFF \
    -DLIBCALIPTRA_DIR=${RECIPE_SYSROOT}${nonarch_base_libdir}/libcaliptra \
    -DLIBCALIPTRA_INCLUDE=${RECIPE_SYSROOT}${includedir}/libcaliptra \
    -DRTL_SOC_IFC_INCLUDE=${RECIPE_SYSROOT}${includedir}/libcaliptra \
"

do_install() {
    install -d ${D}${datadir}/ax3005
    install -m 0644 ${B}/sysmgr.bin ${D}${datadir}/ax3005
}

do_deploy() {
    install -m 0644 ${B}/sysmgr.bin ${DEPLOYDIR}
}

FILES:${PN} = "${datadir}/ax3005/sysmgr.bin"

addtask do_deploy before do_build after do_install
