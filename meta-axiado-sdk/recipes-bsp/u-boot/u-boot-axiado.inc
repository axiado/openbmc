# Copyright (c) 2021-26 Axiado Corporation (or its affiliates). All rights reserved.

SUMMARY = "Axiado uboot recipe"
SECTION = "bootloaders"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;beginline=1;endline=135;md5=99d82354a719c13b8efe19202805e5af"

require recipes-bsp/u-boot/u-boot.inc
PROVIDES += "u-boot"

B = "${UNPACKDIR}/build"

DEPENDS += "dtc-native bison-native"

UBOOT_INITIAL_ENV_BINARY = "1"
UBOOT_INITIAL_ENV_BINARY_SIZE = "0x10000"
