SOC_FAMILY = "axiado-ax3005"
include conf/machine/include/soc-family.inc
include conf/machine/include/arm/arch-arm64.inc

UBOOT_ENTRYPOINT ?= "0x95C00000"
UBOOT_LOADADDRESS ?= "0x95C00000"
UBOOT_DTB_LOADADDRESS ?= "0x99C00000"

PREFERRED_PROVIDER_virtual/kernel ?= "linux-axiado"
PREFERRED_VERSION_linux-axiado ?= "6.18.20+git"
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-axiado"
PREFERRED_PROVIDER_u-boot ?= "u-boot-axiado"
PREFERRED_VERSION_u-boot-axiado ?= "2026.04+git"
PREFERRED_PROVIDER_u-boot-fw-utils ?= "libubootenv"

COMPATIBLE_MACHINE:axiado-ax3005 = "axiado-ax3005"

require conf/machine/include/axiado.inc
