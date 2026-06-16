# Copyright (c) 2021-26 Axiado Corporation (or its affiliates). All rights reserved.

WKS_FILE ?= "emmc-axiado-sdk.wks.in"
# FIT images are built by the linux-yocto-fitimage recipe
WKS_FILE_DEPENDS ?= "virtual/bootloader virtual/kernel linux-yocto-fitimage"

IMAGE_BOOT_FILES ?= "fitImage"
FLASH_KERNEL_IMAGE ?= "fitImage"

DISTROOVERRIDES .= ":axiado-provision"

IMAGE_CLASSES += "image_types_ax3000"

require conf/distro/include/phosphor-mmc.inc
require conf/machine/include/obmc-bsp-common.inc
