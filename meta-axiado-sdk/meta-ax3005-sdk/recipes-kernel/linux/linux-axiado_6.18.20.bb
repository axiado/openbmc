# BU2A-557
SRCREV = "f33595a49cf07c3eec71c70ec4a3459c17cedee2"
SRCBRANCH = "release/release-u-0.2.0"
SRC_URI = "git://bitbucket.org/ax-engg/kernel-6.18.git;protocol=https;branch=${SRCBRANCH}"
SRC_URI += "file://0001-Enable-ARM-TrustZone.patch \
            file://kernel.cfg \
            "

LINUX_VERSION = "6.18.20"
PV = "${LINUX_VERSION}+dev"

require recipes-kernel/linux/linux-axiado.inc
