SRCREV = "c16b75b34b234dac687b626212a1856adf9aa88a"
SRCBRANCH = "release/release-u-0.1.0"
SRC_URI = "git://bitbucket.org/ax-engg/kernel-6.6.git;protocol=https;branch=${SRCBRANCH}"

LINUX_VERSION = "6.6.16"
PV = "${LINUX_VERSION}+dev"

require recipes-kernel/linux/linux-axiado.inc
