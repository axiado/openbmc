SRCREV = "dfbefca80a2128441d19b766a92bb3dafd74ec64"
SRCBRANCH = "release/release-u-0.2.0"
SRC_URI = "git://bitbucket.org/ax-engg/kernel-6.18.git;protocol=https;branch=${SRCBRANCH}"

LINUX_VERSION = "6.18.20"
PV = "${LINUX_VERSION}+dev"

require recipes-kernel/linux/linux-axiado.inc
