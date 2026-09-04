FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://state-directory.conf"

do_install:append() {
    install -d ${D}${sysconfdir}/systemd/system/tee-supplicant@.service.d
    install -m 0644 ${UNPACKDIR}/state-directory.conf \
        ${D}${sysconfdir}/systemd/system/tee-supplicant@.service.d/
}
