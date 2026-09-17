require caliptra-sw-src.inc

PV = "1.2.4+dev"

DEPENDS = "gcc-arm-none-eabi-native"

CALIPTRA_C_FLAGS = "-mcpu=cortex-r52 -marm -mfloat-abi=hard -mfpu=vfpv3-d16 \
                    -mno-unaligned-access -ffreestanding -nostdlib"

do_compile() {
    oe_runmake -C ${S}/libcaliptra \
        RTL_SOC_IFC_INCLUDE_PATH="${S}/hw/1.0/rtl/src/soc_ifc/rtl" \
        CROSS_COMPILE=arm-none-eabi- \
        CALIPTRA_C_FLAGS="${CALIPTRA_C_FLAGS}"
}

do_install() {
    install -d ${D}${includedir}/libcaliptra/
    install -m 0644 ${S}/libcaliptra/inc/*.h ${D}${includedir}/libcaliptra/
    install -m 0644 ${S}/hw/1.0/rtl/src/soc_ifc/rtl/caliptra_top_reg.h ${D}${includedir}/libcaliptra/

    install -d ${D}${nonarch_base_libdir}/libcaliptra
    install -m 0644 ${S}/libcaliptra/libcaliptra.a ${D}${nonarch_base_libdir}/libcaliptra/
}

FILES:${PN}-staticdev = "${nonarch_base_libdir}/libcaliptra/libcaliptra.a"
FILES:${PN}-dev = "${includedir}/libcaliptra/*.h"
