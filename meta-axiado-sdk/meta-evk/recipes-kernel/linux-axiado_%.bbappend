FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append:evk-axiado = " file://axiado-evk-mt2.dts"
SRC_URI:append:evk-axiado = " file://axiado-evk-sb2.dts"
SRC_URI:append:evk-axiado-github = " file://dc-scm.cfg"
SRC_URI:append:evk-axiado-github = " file://dc-scm.scc"
SRC_URI:append:evk-axiado-github = " file://usb-storage.cfg"
SRC_URI:append:evk-axiado-github = " file://usb-storage.scc"

SRC_URI:append:evk-axiado-qemu = " file://ax3000-scm3003-qemu.dts"

do_configure:append:evk-axiado() {
    cp ${UNPACKDIR}/axiado-evk-mt2.dts ${S}/arch/arm64/boot/dts/axiado/
    cp ${UNPACKDIR}/axiado-evk-sb2.dts ${S}/arch/arm64/boot/dts/axiado/
}

do_configure:append:evk-axiado-qemu() {
    cp ${UNPACKDIR}/ax3000-scm3003-qemu.dts ${S}/arch/arm64/boot/dts/axiado/
}
