FILESEXTRAPATHS:append := ":${THISDIR}/${PN}"

OBMC_CONSOLE_HOST_TTY = "ttyS1"

SRC_URI:append = " \
    file://server.ttyS1.conf \
    file://80-obmc-console-ttyS1-uart.rules \
"

do_install:append() {
    install -d ${D}${sysconfdir}/udev/rules.d
    install -m 0644 ${UNPACKDIR}/*.rules ${D}${sysconfdir}/udev/rules.d/
}

FILES_${PN} = "${sysconfdir}/udev/rules.d/80-obmc-console-ttyS1-uart.rules"
