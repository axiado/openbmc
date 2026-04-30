SRCREV = "b4172c7a0fc51d045e0315da49e5b4332d00b9e6"
SRCBRANCH = "release/release-u-0.1.0"
SRC_URI = "git://bitbucket.org/ax-engg/kernel-6.6.git;protocol=https;branch=${SRCBRANCH}"

LINUX_VERSION = "6.6.16"
PV = "${LINUX_VERSION}"

require recipes-kernel/linux/linux-axiado.inc
