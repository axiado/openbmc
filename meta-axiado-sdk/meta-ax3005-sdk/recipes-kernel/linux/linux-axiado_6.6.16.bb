SRCREV = "b9ab52d049e0a3d94fa75c2b9b156741a22acab0"
SRCBRANCH = "release/release-u-0.1.0"
SRC_URI = "git://bitbucket.org/ax-engg/kernel-6.6.git;protocol=https;branch=${SRCBRANCH}"

LINUX_VERSION = "6.6.16"
PV = "${LINUX_VERSION}+dev"

require recipes-kernel/linux/linux-axiado.inc
