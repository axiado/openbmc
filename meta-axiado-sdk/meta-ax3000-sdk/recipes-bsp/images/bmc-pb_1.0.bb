require recipes-bsp/images/bmc-pb.inc

SRCREV = "7b7e64665c32dae6bb3ccc9400e222ad47855a5f"
SRC_URI = "git://bitbucket.org/ax-engg/ax-bsp-prebuild.git;protocol=https;branch=develop"

PV = "1.0+dev"

do_install:append() {
    cp -r ${S}/ax3000/* ${D}${datadir}/axiado
}
