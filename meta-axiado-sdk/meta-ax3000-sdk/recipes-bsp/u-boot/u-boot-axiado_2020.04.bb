require recipes-bsp/u-boot/u-boot-axiado.inc

SRCREV = "e9f129473fce1aa52f198fe24f4fe00e88bee2a7"
SRCBRANCH = "release/release-0.18.0"
SRC_URI = "git://bitbucket.org/ax-engg/u-boot.git;protocol=https;branch=${SRCBRANCH}"

PV = "2020.04+dev"
