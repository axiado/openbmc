# Copyright (c) 2021-26 Axiado Corporation (or its affiliates). All rights reserved.

DESCRIPTION = "Linux kernel for Axiado"
SECTION = "kernel"
LICENSE = "GPL-2.0-only"

LIC_FILES_CHKSUM ?= "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

PROVIDES += "virtual/kernel"

KCONFIG_MODE = "--alldefconfig"

inherit kernel kernel-fit-extra-artifacts
require recipes-kernel/linux/linux-yocto.inc

ERROR_QA:remove = "buildpaths"
WARN_QA:append = "buildpaths"

KERNEL_IMAGETYPES += "vmlinux"

# Linux don't support zImage for ARM64 platform
KERNEL_IMAGETYPE:remove = "zImage"
KERNEL_IMAGETYPES:remove = "zImage"

