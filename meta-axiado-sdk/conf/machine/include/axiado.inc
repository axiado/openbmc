# Copyright (c) 2021-26 Axiado Corporation (or its affiliates). All rights reserved.

# Build var, valid values are ["dbg", "dev", "prod"]
# Dbg builds initramfs image with filesystem + kernel in a single image and u-boot
# Dev builds kernel-fitImage and eMMC image with wic filesystem and u-boot
# Prod builds kernel-fitImage and eMMC image with wic filesystem and Axiado-signed u-boot
# For modifying this value, add BUILD_VAR = "dev" in local.conf file under build/<machine>
BUILD_VAR ??= "prod"
require conf/machine/include/axiado-${BUILD_VAR}.inc

INITRAMFS_IMAGE_BUNDLE = "1"
