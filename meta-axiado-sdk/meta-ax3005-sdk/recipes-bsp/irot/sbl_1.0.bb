SUMMARY = "Axiado Secondary bootloader"
LICENSE = "CLOSED"

PV = "1.0+dev"

require irot.inc

OECMAKE_SOURCEPATH = "${S}/boot"

# This is a Cortex-R52 baremetal firmware build, so it must use the project's
# own arm-none-eabi toolchain file instead of cmake.bbclass' generated OE
# (aarch64/Linux) one. EXTRA_OECMAKE is appended after OECMAKE_ARGS on the
# cmake command line, so this -DCMAKE_TOOLCHAIN_FILE overrides the class'
# ${WORKDIR}/toolchain.cmake. arm-none-eabi.cmake sets the R52 compilers and
# CMAKE_SYSTEM_NAME=Generic before project(), which the CMakeLists requires.
EXTRA_OECMAKE = "\
    -DBOOT_MODE=sbl \
    -DSOC_NAME=AX3005 \
    -DSOC_REV=ULTRA \
    -DCORE_NUM=CORE0 \
    -DPLATFORM_FLAG=SOC \
    -DCMAKE_BUILD_TYPE=AXRELEASE \
    -DCMAKE_TOOLCHAIN_FILE=${S}/sysmgr_ultra/arm-none-eabi.cmake \
    -DTOOLCHAIN_PATH=${STAGING_DIR_NATIVE}/usr \
    -DCMAKE_SYSROOT=${RECIPE_SYSROOT} \
    -DRECIPE_SYSROOT_INCLUDE_DIR=${RECIPE_SYSROOT}${includedir}/ax-bsp-headers \
    -DBUILD_LIBCALIPTRA=OFF \
    -DLIBCALIPTRA_DIR=${RECIPE_SYSROOT}${nonarch_base_libdir}/libcaliptra \
    -DLIBCALIPTRA_INCLUDE=${RECIPE_SYSROOT}${includedir}/libcaliptra \
    -DRTL_SOC_IFC_INCLUDE=${RECIPE_SYSROOT}${includedir}/libcaliptra \
"

do_install() {
    install -d ${D}${datadir}/ax3005
    install -m 0644 ${B}/sbl.bin ${D}${datadir}/ax3005
}

do_deploy() {
    install -m 0644 ${B}/sbl.bin ${DEPLOYDIR}
}

FILES:${PN} = "${datadir}/ax3005/sbl.bin"

addtask do_deploy before do_build after do_install
