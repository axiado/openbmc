# Copyright (c) 2021-26 Axiado Corporation (or its affiliates). All rights reserved.

SUMMARY = "Axiado uboot recipe"
SECTION = "bootloaders"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://README;beginline=1;endline=4;md5=c5130931598a8ad21840e124ffe64ea0"

require recipes-bsp/u-boot/u-boot.inc
PROVIDES += "u-boot"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

DEPENDS += " dtc-native bison-native"

UBOOT_ENV_SIZE ?= "0x10000"
UBOOT_ENV = "u-boot-env"
UBOOT_ENV_SUFFIX = "bin"
