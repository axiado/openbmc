SUMMARY = "Axiado EIP IP firmware blobs"
DESCRIPTION = "EIP-207 classification firmware files needed during early boot"
LICENSE = "CLOSED"

DEPENDS += "bmc-pb"

do_install[depends] += "bmc-pb:do_populate_sysroot"

EIP_FIRMWARE_DIR = "${RECIPE_SYSROOT}${datadir}/axiado/images"

do_install() {
    install -d ${D}${nonarch_base_libdir}/firmware
    install -m 0644 ${EIP_FIRMWARE_DIR}/firmware_eip207_ifpp.bin ${D}${nonarch_base_libdir}/firmware/
    install -m 0644 ${EIP_FIRMWARE_DIR}/firmware_eip207_ipue.bin ${D}${nonarch_base_libdir}/firmware/
    install -m 0644 ${EIP_FIRMWARE_DIR}/firmware_eip207_ofpp.bin ${D}${nonarch_base_libdir}/firmware/
    install -m 0644 ${EIP_FIRMWARE_DIR}/firmware_eip207_opue.bin ${D}${nonarch_base_libdir}/firmware/
}

FILES:${PN} = "${nonarch_base_libdir}/firmware"
