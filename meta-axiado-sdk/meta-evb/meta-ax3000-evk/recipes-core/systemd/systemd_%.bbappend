FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://systemd-networkd-only-wait-for-eth0.conf"

do_install:append() {
    install -m 644 -D \
        ${UNPACKDIR}/systemd-networkd-only-wait-for-eth0.conf \
        ${D}${systemd_system_unitdir}/systemd-networkd-wait-online.service.d/systemd-networkd-only-wait-for-eth0.conf
}
