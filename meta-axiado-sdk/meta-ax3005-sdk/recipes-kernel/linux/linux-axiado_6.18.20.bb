# BU2A-559
SRCREV = "d3ad87f49f72fb0bda9d0ba258599c45e34fa698"
SRCBRANCH = "release/release-u-0.2.0"
SRC_URI = "git://bitbucket.org/ax-engg/kernel-6.18.git;protocol=https;branch=${SRCBRANCH}"
SRC_URI += "file://0001-Enable-ARM-TrustZone.patch \
            file://kernel.cfg \
            "

LINUX_VERSION = "6.18.20"
PV = "${LINUX_VERSION}+dev"

require recipes-kernel/linux/linux-axiado.inc
