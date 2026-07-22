SRCREV = "377db94296cdd710d2a1d242f92f8b730c4088a8"
SRCBRANCH = "release/release-u-0.1.0"
SRC_URI = "git://bitbucket.org/ax-engg/kernel-6.6.git;protocol=https;branch=${SRCBRANCH}"

LINUX_VERSION = "6.6.16"
PV = "${LINUX_VERSION}+dev"

require recipes-kernel/linux/linux-axiado.inc
