SRCBRANCH = "dev-6.6-axiado"
SRCREV = "9b9e4b85391a4915f1b87d55b1cec1571e595c39"
SRC_URI = "git://github.com/axiado/linux-axiado;protocol=https;branch=${SRCBRANCH}"
SRC_URI += "file://kernel.scc \
            file://kernel.cfg \
            "

LINUX_VERSION = "6.6.106"
PV = "${LINUX_VERSION}+git"

require recipes-kernel/linux/linux-axiado.inc
