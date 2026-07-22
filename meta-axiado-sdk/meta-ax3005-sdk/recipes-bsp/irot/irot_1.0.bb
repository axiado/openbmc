SUMMARY = "Axiado internal Root of Trust bootflow component"
LICENSE = "CLOSED"

PV = "1.0+dev"

inherit deploy cmake

SRCBRANCH = "release/release-u-0.2.0"

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
           "

# BU2-551
SRCREV_kw-hal = "d2f92649dae2e7bb7aff3efac886c9e6e53324a1"
SRCREV_ax-hal = "0944df4675c266c1da15f8e7164bfed3bb9538a0"
SRCREV_bali-hal = "505d66659ec726911a41f9d6599a054a83f6c93f"
SRCREV_caliptra-sw = "f6999a2ee7428f03afb926e5f328479be7b4ea1d"

DEPENDS = "cmake-native gcc-arm-none-eabi-native kw-hal ax-bsp-headers"

OECMAKE_SOURCEPATH = "${S}/sysmgr"

OECMAKE_GENERATOR = "Unix Makefiles"

# This is a Cortex-R52 baremetal firmware build, so it must use the project's
# own arm-none-eabi toolchain file instead of cmake.bbclass' generated OE
# (aarch64/Linux) one. EXTRA_OECMAKE is appended after OECMAKE_ARGS on the
# cmake command line, so this -DCMAKE_TOOLCHAIN_FILE overrides the class'
# ${WORKDIR}/toolchain.cmake. arm-none-eabi.cmake sets the R52 compilers and
# CMAKE_SYSTEM_NAME=Generic before project(), which the CMakeLists requires.
EXTRA_OECMAKE = "\
    -DCMAKE_TOOLCHAIN_FILE=${S}/sysmgr/arm-none-eabi.cmake \
    -DTOOLCHAIN_PATH=${STAGING_DIR_NATIVE}/usr \
    -DCMAKE_SYSROOT=${RECIPE_SYSROOT} \
    -DRECIPE_SYSROOT_INCLUDE_DIR=${RECIPE_SYSROOT}${includedir}/ax-bsp-headers \
    -DKW_ROOT=${S} \
    -DCORE_NUM=CORE0 \
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
