# Copyright (c) 2021-26 Axiado Corporation (or its affiliates). All rights reserved.

SUMMARY = "Bali HAL static libraries for Axiado AX-Ultra platform (Cortex-R52 bare-metal)"
SECTION = "axiado/firmware"

# Proprietary Axiado code; third-party components have their own licenses
# documented in bali_hal/LICENSES/ (FreeRTOS-MIT, CMSIS-Apache-2.0, mbedTLS-Apache-2.0)
LICENSE = "CLOSED"

SRCREV_FORMAT = "kw-hal_ax-hal_bali-hal"
SRC_URI = "${SRC_URI_KW_HAL};branch=${SRCBRANCH};name=kw-hal \
           ${SRC_URI_AX_HAL};branch=${SRCBRANCH};name=ax-hal;destsuffix=${P}/ax_hal \
           ${SRC_URI_BAIL_HAL};branch=${SRCBRANCH};name=bali-hal;destsuffix=${P}/bali_hal \
           "

# gcc-arm-none-eabi-native supplies arm-none-eabi-gcc from the Yocto-managed
# meta-arm-toolchain layer (no system compiler dependency).
# cmake-native provides cmake.
DEPENDS = "cmake-native gcc-arm-none-eabi-native"

# Do not inherit the cmake class — it injects the aarch64 sysroot cross-compiler
# which conflicts with the bare-metal arm-none-eabi toolchain required here.
do_configure() {
    mkdir -p ${B}
    cmake -S ${S}/bali_hal -B ${B} \
        -DPLATFORM_TYPE=axiado \
        -DTARGET_PLATFORM=ax-ultra \
        -DCORE_NUM=CORE0 \
        -DCMAKE_TOOLCHAIN_FILE=${S}/bali_hal/system/axiado/arm-none-eabi.cmake \
        -DTOOLCHAIN_PATH=${STAGING_DIR_NATIVE}/usr \
        -DTEST_TYPE_UNIT=OFF \
        -DTEST_TYPE_INTEGRATION=OFF \
        -DTEST_TYPE_SYSTEM=OFF \
        -DTEST_TYPE_PERFORMANCE=OFF \
        -DTEST_TYPE_ALL=OFF
}

do_compile() {
    cmake --build ${B}
}

do_install() {
    install -d ${D}${libdir}/bali-hal

    # Collect static libs from the cmake build dir and from ax_hal which CMake
    # builds into an absolute path (${S}/ax_hal/build) via add_subdirectory.
    find ${B} ${S}/ax_hal/build -name "*.a" 2>/dev/null | while read lib; do
        install -m 0644 "$lib" ${D}${libdir}/bali-hal/
    done

    # bali_hal public headers
    install -d ${D}${includedir}/bali-hal
    find ${S}/bali_hal/include -name "*.h" | while read hdr; do
        install -m 0644 "$hdr" ${D}${includedir}/bali-hal/
    done

    # ax_hal public headers
    install -d ${D}${includedir}/ax-hal
    find ${S}/ax_hal/include -name "*.h" | while read hdr; do
        install -m 0644 "$hdr" ${D}${includedir}/ax-hal/
    done

    # base (sysmgr) public headers
    install -d ${D}${includedir}/base-hal
    find ${S}/base/include -name "*.h" | while read hdr; do
        install -m 0644 "$hdr" ${D}${includedir}/base-hal/
    done
}

# Static archives contain debug symbols with absolute build paths (-g flag); expected for bare-metal libs.
INSANE_SKIP:${PN}-staticdev += "buildpaths"

FILES:${PN}-staticdev += "${libdir}/bali-hal/*.a"
FILES:${PN}-dev += " \
    ${includedir}/bali-hal \
    ${includedir}/ax-hal \
    ${includedir}/base-hal \
"
