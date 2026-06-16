SRCREV = "45fc937a58649da9e10309cc94408d68751719c1"
SRCBRANCH = "release/release-u-0.2.0"
SRC_URI = "git://bitbucket.org/ax-engg/kernel-6.18.git;protocol=https;branch=${SRCBRANCH}"

LINUX_VERSION = "6.18.20"
PV = "${LINUX_VERSION}+dev"

require recipes-kernel/linux/linux-axiado.inc
