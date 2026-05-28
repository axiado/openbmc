SRCREV = "7c34d22b8f9ff650218e43a9ba81c1f425b36f27"
SRCBRANCH = "dev-6.6-axiado-ax3005"
SRC_URI = "git://github.com/axiado/linux-axiado;protocol=https;branch=${SRCBRANCH}"

LINUX_VERSION = "6.6.106"
PV = "${LINUX_VERSION}+git"

require recipes-kernel/linux/linux-axiado.inc
