SRCREV = "509cde4fb3f584912f78deebd8012d70cbe931fd"
SRCBRANCH = "dev-6.6-axiado-ax3005"
SRC_URI = "git://github.com/axiado/linux-axiado;protocol=https;branch=${SRCBRANCH}"

LINUX_VERSION = "6.6.106"
PV = "${LINUX_VERSION}+git"

require recipes-kernel/linux/linux-axiado.inc
