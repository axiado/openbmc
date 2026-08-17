# BU2-553
SRCREV = "4f98e10f8ccd540729dd6e6b23cf044786628c6c"
SRCBRANCH = "release/release-u-0.2.0"
SRC_URI = "git://bitbucket.org/ax-engg/kernel-6.18.git;protocol=https;branch=${SRCBRANCH}"
SRC_URI += "file://kernel.cfg"

LINUX_VERSION = "6.18.20"
PV = "${LINUX_VERSION}+dev"

require recipes-kernel/linux/linux-axiado.inc
