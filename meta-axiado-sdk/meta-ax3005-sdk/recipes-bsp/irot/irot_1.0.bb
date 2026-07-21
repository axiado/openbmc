SUMMARY = "Axiado internal Root of Trust bootflow component"
LICENSE = "CLOSED"

PV = "1.0+dev"

inherit deploy cmake

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
           "

SRCREV_kw-hal = "944a8f94c33cc8fb6f33fb2e442251c3be8db528"
SRCREV_ax-hal = "96521267b5fb362b8e25fb911511e0fedd5d116a"
SRCREV_bali-hal = "7eaec22a13c434977e982d756408648b6827db41"
SRCREV_caliptra-sw = "320f75fca34c59de60a022908090f6a277caa63c"

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
