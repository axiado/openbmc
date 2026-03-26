FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://ax3000-scm3003-qemu.dts"

do_configure:append() {
    cp ${UNPACKDIR}/ax3000-scm3003-qemu.dts ${S}/arch/arm64/boot/dts/axiado/
}
