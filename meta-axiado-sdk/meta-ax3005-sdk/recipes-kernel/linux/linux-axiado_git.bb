SRCREV = "bec4a5c80c1871bdca4fdf7d9034ba3dff519828"
SRCBRANCH = "dev-6.18-axiado"
SRC_URI = "git://github.com/axiado/linux-axiado;protocol=https;branch=${SRCBRANCH}"
SRC_URI += "file://0001-Enable-ARM-TrustZone.patch \
            file://kernel.cfg \
            "

LINUX_VERSION = "6.18.20"
PV = "${LINUX_VERSION}+git"

require recipes-kernel/linux/linux-axiado.inc
