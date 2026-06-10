require recipes-bsp/images/bmc-pb.inc

SRCREV = "71b5accfddaec04500858dd6d246c13feda6561d"
SRC_URI = "git://bitbucket.org/ax-engg/ax-bsp-prebuild.git;protocol=https;branch=develop"

PV = "1.0+dev"

do_install:append() {
    cp -r ${S}/ax3000/* ${D}${datadir}/axiado
}
