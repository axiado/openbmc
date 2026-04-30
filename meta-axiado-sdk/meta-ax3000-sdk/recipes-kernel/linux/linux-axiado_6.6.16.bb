SRCREV = "7a291901f186933b138f0655896d134fbbd26c67"
SRCBRANCH = "release/release-0.18.0"
SRC_URI = "git://bitbucket.org/ax-engg/kernel-6.6.git;protocol=https;branch=${SRCBRANCH}"

LINUX_VERSION = "6.6.16"
PV = "${LINUX_VERSION}"

require recipes-kernel/linux/linux-axiado.inc
