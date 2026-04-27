FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://ax3005-evb-qemu.dts"
SRC_URI += "file://0001-Disable-CQE-support-in-SDHCI-driver.patch"

do_configure:append() {
    cp ${UNPACKDIR}/ax3005-evb-qemu.dts ${S}/arch/arm64/boot/dts/axiado/
}
