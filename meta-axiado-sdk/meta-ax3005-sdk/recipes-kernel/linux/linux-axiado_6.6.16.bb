SRCREV = "0f501d2b4644f872387329922a58f8686f0c25a9"
SRCBRANCH = "release/release-u-0.1.0"
SRC_URI = "git://bitbucket.org/ax-engg/kernel-6.6.git;protocol=https;branch=${SRCBRANCH}"
SRC_URI += "file://phram.cfg"

LINUX_VERSION = "6.6.16"

require recipes-kernel/linux/linux-axiado.inc
