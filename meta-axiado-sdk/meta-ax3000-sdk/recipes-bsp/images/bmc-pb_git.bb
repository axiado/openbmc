require recipes-bsp/images/bmc-pb.inc

SRCREV = "84a31e920cc0b2ba69ec296fe7dd120755377545"
SRC_URI = "git://git@github.com/axiado/bmc_pb;protocol=ssh;branch=develop"

PV = "1.0+git"

do_install:append() {
    cp -r ${S}/ax3000/* ${D}${datadir}/axiado
}
