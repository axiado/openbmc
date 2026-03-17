# Copyright (c) 2021-26 Axiado Corporation (or its affiliates). All rights reserved.

KERNEL_IMAGETYPE ?= "Image"
FLASH_KERNEL_IMAGE ?= "Image"
INITRAMFS_IMAGE = "core-image-minimal"
INITRAMFS_MAXSIZE = "631072"
INITRAMFS_CTYPE ?= "xz"
INITRAMFS_FSTYPES = "cpio.${INITRAMFS_CTYPE}"
IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"

CORE_IMAGE_EXTRA_INSTALL += "axiado-eip-firmware ax-net iptables iproute2 net-tools"
CORE_IMAGE_EXTRA_INSTALL += "dropbear xz"

inherit extrausers
DEFAULT_OPENBMC_PASSWORD = "'\$6\$UGMqyqdG\$GqTb3tXPFx9AJlzTw/8X5RoW2Z.100dT.acuk8AFJfNQYr.ZRL8itMIgLqsdq46RNHgiv78XayOSl.IbR4DFU.'"
EXTRA_USERS_PARAMS = "usermod -p ${DEFAULT_OPENBMC_PASSWORD} root;"
