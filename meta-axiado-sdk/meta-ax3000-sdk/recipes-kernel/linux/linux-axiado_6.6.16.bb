SRCREV = "4497cbae86237274b7d1c0fe9149bf0deeb3f72d"
SRCBRANCH = "release/release-0.18.0"
SRC_URI = "git://bitbucket.org/ax-engg/kernel-6.6.git;protocol=https;branch=${SRCBRANCH}"

LINUX_VERSION = "6.6.16"
PV = "${LINUX_VERSION}+dev"

require recipes-kernel/linux/linux-axiado.inc
