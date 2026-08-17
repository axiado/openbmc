SRCREV = "89ce7062c57ed2e1e854eb31c4430d66e7dfa537"
SRCBRANCH = "release/release-0.18.0"
SRC_URI = "git://bitbucket.org/ax-engg/kernel-6.6.git;protocol=https;branch=${SRCBRANCH}"

LINUX_VERSION = "6.6.16"
PV = "${LINUX_VERSION}+dev"

require recipes-kernel/linux/linux-axiado.inc
