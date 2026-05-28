# Copyright (c) 2021-26 Axiado Corporation (or its affiliates). All rights reserved.
SUMMARY = "Log Manager"
DESCRIPTION = "Log Manager application to manage TCU logs"
LICENSE = "CLOSED"
PV = "1.0+git"

inherit obmc-phosphor-systemd

SRC_URI = "file://ax_logger.service \
           file://ax_logger \
           "

ERROR_QA:remove = "buildpaths"
WARNNING_QA:append = "buildpaths"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${UNPACKDIR}/ax_logger ${D}${bindir}
}

SYSTEMD_SERVICE:${PN} = "ax_logger.service"
FILES:${PN} = "${bindir}/ax_logger"
