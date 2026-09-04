SRCREV = "7c374df5a84a15e4eae653aa221798cb14411cf4"
SRCBRANCH = "dev-6.18-axiado"
SRC_URI = "git://github.com/axiado/linux-axiado;protocol=https;branch=${SRCBRANCH}"
SRC_URI += "file://0001-Enable-ARM-TrustZone.patch \
            file://kernel.cfg \
            "

LINUX_VERSION = "6.18.20"
PV = "${LINUX_VERSION}+git"

require recipes-kernel/linux/linux-axiado.inc
