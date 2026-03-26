FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://axiado-evk-mt2.dts \
            file://axiado-evk-sb2.dts \
            "

do_configure:append() {
    cp ${UNPACKDIR}/axiado-evk-mt2.dts ${S}/arch/arm64/boot/dts/axiado/
    cp ${UNPACKDIR}/axiado-evk-sb2.dts ${S}/arch/arm64/boot/dts/axiado/
}
