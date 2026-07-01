require recipes-bsp/images/bmc-pb.inc

SRCREV = "68b567afb1d2cf7a9211a71f97f5b85101bb9975"
SRC_URI = "git://git@github.com/axiado/bmc_pb;protocol=ssh;branch=develop"
SRC_URI += "file://ax-images.toml"

PV = "1.0+git"

do_configure() {
    install -m 0644 ${UNPACKDIR}/ax-images.toml ${S}/ax3005
}

do_install:append() {
    cp -r ${S}/ax3005/* ${D}${datadir}/axiado
}

do_deploy:append() {
    install -m 0644 ${S}/ax3005/images/image-bundle.bin ${DEPLOYDIR}
    install -m 0644 ${S}/ax3005/images/sbl.bin ${DEPLOYDIR}
    install -m 0644 ${S}/ax3005/images/secmc.img.ebin ${DEPLOYDIR}
    install -m 0644 ${S}/ax3005/images/sysmgr.bin ${DEPLOYDIR}
}
