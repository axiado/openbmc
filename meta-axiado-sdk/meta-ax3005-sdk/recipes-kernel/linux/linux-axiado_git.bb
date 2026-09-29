SRCREV = "9d2b74291ca1f012c6e51f1e13fb3003263e57e7"
SRCBRANCH = "dev-6.18-axiado"
SRC_URI = "git://github.com/axiado/linux-axiado;protocol=https;branch=${SRCBRANCH}"
SRC_URI += "file://0001-Enable-ARM-TrustZone.patch \
            file://kernel.cfg \
            "

LINUX_VERSION = "6.18.20"
PV = "${LINUX_VERSION}+git"

require recipes-kernel/linux/linux-axiado.inc
