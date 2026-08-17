SRCBRANCH = "dev-6.6-axiado"
SRCREV = "7ac672345707718fb893e450f0767438c7a1854d"
SRC_URI = "git://github.com/axiado/linux-axiado;protocol=https;branch=${SRCBRANCH}"
SRC_URI += "file://kernel.scc \
            file://kernel.cfg \
            "

LINUX_VERSION = "6.6.106"
PV = "${LINUX_VERSION}+git"

require recipes-kernel/linux/linux-axiado.inc
