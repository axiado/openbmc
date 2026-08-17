require recipes-bsp/u-boot/u-boot-axiado.inc

SRCBRANCH = "master"
SRC_URI = "git://github.com/axiado/u-boot-ax3005;protocol=https;branch=${SRCBRANCH}"
SRCREV = "1ba4cd2c115767b5ad7df0b35a8f9340bc7d6269"

PV = "2026.04+git"
