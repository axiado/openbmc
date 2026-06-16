# Copyright (c) 2021-26 Axiado Corporation (or its affiliates). All rights reserved.

FILESEXTRAPATHS:append := "${THISDIR}/${PN}:"

SUMMARY = "DTM AGENT"
SECTION = "DTM AGENT RUNNER"
LICENSE = "CLOSED"
PV = "1.0"

LIC_FILES_CHKSUM ?= "file://COPYING.axiado;md5=3da9cfbcb788c80a0384361b4de20420"

SRCBRANCH ?= "release/release-0.18.0"
SRCREV ?= "b3601342e92a31789cb2036bf69773de79c6c11c"
SRC_URI =  "git://bitbucket.org/ax-engg/dtm-agent.git;protocol=https;branch=${SRCBRANCH}"

inherit meson pkgconfig
inherit obmc-phosphor-systemd

DEPENDS += "systemd cjson"

do_install() {
    install -d ${D}${sysconfdir}
    install -d ${D}${bindir}
    install -d ${D}${systemd_system_unitdir}
    install -p -m 644 ${S}/dtm-agent/conf/${MACHINE}/dtmconf.json ${D}${sysconfdir}/dtmconf.json
    install -p -m 644 ${B}/dtmagent ${D}${bindir}/dtmagent
    install -p -m 644 ${S}/dtm-agent.service ${D}${systemd_system_unitdir}/dtm-agent.service
}

SYSTEMD_SERVICE:${PN} = "dtm-agent.service"
