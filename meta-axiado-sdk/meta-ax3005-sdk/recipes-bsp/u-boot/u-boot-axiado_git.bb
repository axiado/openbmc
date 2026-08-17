require recipes-bsp/u-boot/u-boot-axiado.inc

SRCREV = "254e50d5acd330cb628d6ce687be185ced4fa074"
SRCBRANCH = "master"
SRC_URI = "git://github.com/axiado/u-boot-ax3005;protocol=https;branch=${SRCBRANCH}"

PV = "2026.04+git"
