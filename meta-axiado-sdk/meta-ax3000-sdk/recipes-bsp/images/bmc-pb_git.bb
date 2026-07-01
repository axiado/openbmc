require recipes-bsp/images/bmc-pb.inc

SRCREV = "6cbce897a823824f434bfea6415648ee1b4536d2"
SRC_URI = "git://git@github.com/axiado/bmc_pb;protocol=ssh;branch=develop"

PV = "1.0+git"

do_install:append() {
    cp -r ${S}/ax3000/* ${D}${datadir}/axiado
}
