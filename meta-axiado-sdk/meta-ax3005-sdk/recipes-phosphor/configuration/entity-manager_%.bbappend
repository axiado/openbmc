FILESEXTRAPATHS:append := ":${THISDIR}/files"

SRC_URI += "file://ax3005.json"

do_install:append() {
     install -m 0444 ${UNPACKDIR}/ax3005.json ${D}/usr/share/entity-manager/configurations/axiado/
}
