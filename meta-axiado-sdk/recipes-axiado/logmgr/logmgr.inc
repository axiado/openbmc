# Copyright (c) 2021-26 Axiado Corporation (or its affiliates). All rights reserved.
SUMMARY = "Log Manager"
DESCRIPTION = "Log Manager application to manage TCU logs"
LICENSE = "CLOSED"

LIC_FILES_CHKSUM ?= "file://COPYING.axiado;md5=01d0d9bdb04606d39dcbff1ca352f133"

inherit meson pkgconfig
inherit obmc-phosphor-systemd

DEPENDS += "systemd"

SYSTEMD_SERVICE:${PN} = "ax_logger.service"
