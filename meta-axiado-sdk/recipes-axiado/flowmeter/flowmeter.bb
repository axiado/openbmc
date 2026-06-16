# Copyright (c) 2021-26 Axiado Corporation (or its affiliates). All rights reserved.

FILESEXTRAPATHS:append := "${THISDIR}/${PN}:"

SUMMARY = "Flowmeter"
SECTION = "Flowmeter demo"
LICENSE = "CLOSED"
PV = "1.0"

LIC_FILES_CHKSUM ?= "file://COPYING.axiado;md5=3da9cfbcb788c80a0384361b4de20420"

SRCBRANCH ?= "release/release-0.18.0"
SRCREV ?= "890a2cbd3b64760040321e3df87347690dcf07be"
SRC_URI = "git://bitbucket.org/ax-engg/flowmeter.git;protocol=https;branch=${SRCBRANCH}"

inherit meson pkgconfig
inherit obmc-phosphor-systemd

DEPENDS += "libpcap systemd"

SYSTEMD_SERVICE:${PN} = "flowmeter.service rwui.service"
