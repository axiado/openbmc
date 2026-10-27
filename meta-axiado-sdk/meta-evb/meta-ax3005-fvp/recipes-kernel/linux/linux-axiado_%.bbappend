FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append:ax3005-fvp = " file://ax3005-fvp.dts"
SRC_URI:append:ax3005-fvp-github = " file://ax3005-fvp-github.dts"

SRC_URI += "file://0001-Port-OpenSMA-to-a-Linux-USB-Gadget-kernel-module.patch \
           "

do_configure:append:ax3005-fvp() {
    cp ${UNPACKDIR}/ax3005-fvp.dts ${S}/arch/arm64/boot/dts/axiado/
}

do_configure:append:ax3005-fvp-github() {
    cp ${UNPACKDIR}/ax3005-fvp-github.dts ${S}/arch/arm64/boot/dts/axiado/
}
