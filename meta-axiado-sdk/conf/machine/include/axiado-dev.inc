# Copyright (c) 2021-26 Axiado Corporation (or its affiliates). All rights reserved.

WKS_FILE ?= "emmc-axiado.wks.in"
WKS_FILE_DEPENDS ?= "virtual/bootloader virtual/kernel"

IMAGE_BOOT_FILES ?= "fitImage"
KERNEL_IMAGETYPE ?= "fitImage"
FLASH_KERNEL_IMAGE ?= "fitImage"

FLASH_UBOOT_OFFSET = "0"

require conf/distro/include/phosphor-mmc.inc
require conf/machine/include/obmc-bsp-common.inc
