FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append = " \
  file://mctp-local.service \
  file://mctpd.conf \
  file://run_mctp_usb.sh \
  file://run_mctp_i2c.sh \
  file://run_mctp_i3c.sh \
"

SYSTEMD_SERVICE:${PN} += "mctp-local.service"

do_install:append() {
    install -m 0644 ${UNPACKDIR}/mctp-local.service ${D}${systemd_system_unitdir}/
    install -d ${D}/etc/
    install -m 0644 ${UNPACKDIR}/mctpd.conf ${D}/etc/mctpd.conf

    install -d ${D}${bindir}
    install -m 0755 ${UNPACKDIR}/run_*.sh ${D}${bindir}/
}

