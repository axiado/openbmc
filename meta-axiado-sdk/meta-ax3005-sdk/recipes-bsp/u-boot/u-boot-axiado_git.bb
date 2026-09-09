require recipes-bsp/u-boot/u-boot-axiado.inc

SRCREV = "b7f093326e47d9b44600267df720026bb8a06acc"
SRCBRANCH = "master"
SRC_URI = "git://github.com/axiado/u-boot-ax3005;protocol=https;branch=${SRCBRANCH}"

PV = "2026.04+git"
