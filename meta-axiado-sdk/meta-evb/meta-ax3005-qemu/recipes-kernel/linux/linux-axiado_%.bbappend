FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://ax3005-evb-qemu.dts"

do_configure:append() {
    cp ${UNPACKDIR}/ax3005-evb-qemu.dts ${S}/arch/arm64/boot/dts/axiado/
}
