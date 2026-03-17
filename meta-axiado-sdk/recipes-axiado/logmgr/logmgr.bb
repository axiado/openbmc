# Copyright (c) 2021-26 Axiado Corporation (or its affiliates). All rights reserved.

FILESEXTRAPATHS:append := "${THISDIR}/${PN}:"

SUMMARY = "Log Manager"
DESCRIPTION = "Log Manager application to manage TCU logs"
LICENSE = "CLOSED"
PV = "1.0"

LIC_FILES_CHKSUM ?= "file://COPYING.axiado;md5=01d0d9bdb04606d39dcbff1ca352f133"

SRCBRANCH ?= "${LATEST_RELEASE_VERSION}"
SRCREV ?= "3d39ccb18da019472f35e184ccaefa35625c07be"
SRC_URI =  "git://git@sourcevault.axiadord:7999/apps/logmgr.git;protocol=ssh;branch=${SRCBRANCH}"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

inherit meson pkgconfig
inherit obmc-phosphor-systemd

DEPENDS += "systemd"

SYSTEMD_SERVICE:${PN} = "ax_logger.service"
