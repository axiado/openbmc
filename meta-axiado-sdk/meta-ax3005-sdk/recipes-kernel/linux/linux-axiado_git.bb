SRCREV = "38f1c6feecff6ab6302def7811a3af715e397286"
SRCBRANCH = "dev-6.6-axiado-ax3005"
SRC_URI = "git://github.com/axiado/linux-axiado;protocol=https;branch=${SRCBRANCH}"

LINUX_VERSION = "6.6.106"
PV = "${LINUX_VERSION}+git"

require recipes-kernel/linux/linux-axiado.inc
