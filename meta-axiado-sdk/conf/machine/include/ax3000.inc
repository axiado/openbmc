SOC_FAMILY = "axiado-ax3000"
include conf/machine/include/soc-family.inc
include conf/machine/include/arm/arch-arm64.inc

UBOOT_ENTRYPOINT ?= "0x3E000000"
UBOOT_LOADADDRESS ?= "0x3E000000"
UBOOT_DTB_LOADADDRESS ?= "0x40000000"

require conf/machine/include/axiado.inc
