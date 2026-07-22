SRCREV = "1a1d852d66ff350ffba612a1fa91cc714cae0ea8"
SRCBRANCH = "dev-6.18-axiado"
SRC_URI = "git://github.com/axiado/linux-axiado;protocol=https;branch=${SRCBRANCH}"

LINUX_VERSION = "6.18.20"
PV = "${LINUX_VERSION}+git"

require recipes-kernel/linux/linux-axiado.inc
