# Copyright (c) 2021-26 Axiado Corporation (or its affiliates). All rights reserved.

require conf/machine/include/obmc-bsp-common.inc
require conf/distro/include/pldm.inc

IMAGE_BOOT_FILES = "fitImage"
FLASH_KERNEL_IMAGE = "fitImage"

FLASH_UBOOT_OFFSET = "0"
FLASH_UBOOT_ENV_OFFSET = "1024"
FLASH_KERNEL_OFFSET = "1024"
FLASH_ROFS_OFFSET = "11264"
FLASH_RWFS_OFFSET = "65535"

WKS_FILE ?= "emmc-ax3005-sdk.wks.in"
WKS_FILE_DEPENDS:append = " virtual/bootloader"
IMAGE_FSTYPES += "wic.xz"

IMAGE_CLASSES += "image_types_ax3005"
