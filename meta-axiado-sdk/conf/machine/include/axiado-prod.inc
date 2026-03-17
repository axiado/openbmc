# Copyright (c) 2021-26 Axiado Corporation (or its affiliates). All rights reserved.

WKS_FILE ?= "emmc-axiado.wks.in"
WKS_FILE_DEPENDS ?= "virtual/bootloader virtual/kernel"

IMAGE_BOOT_FILES ?= "fitImage"
KERNEL_IMAGETYPE ?= "fitImage"
FLASH_KERNEL_IMAGE ?= "fitImage"

DISTROOVERRIDES .= ":axiado-provision"

IMAGE_CLASSES += "image_types_ax3000"

require conf/distro/include/phosphor-mmc.inc
require conf/machine/include/obmc-bsp-common.inc
