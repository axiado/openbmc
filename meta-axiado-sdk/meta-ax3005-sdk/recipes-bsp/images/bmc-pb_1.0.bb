require recipes-bsp/images/bmc-pb.inc

SRCREV = "71b5accfddaec04500858dd6d246c13feda6561d"
SRC_URI = "git://bitbucket.org/ax-engg/ax-bsp-prebuild.git;protocol=https;branch=develop"
SRC_URI += "file://ax-images.toml"

PV = "1.0+dev"

do_configure() {
    install -m 0644 ${UNPACKDIR}/ax-images.toml ${S}/ax3005
}

do_install:append() {
    cp -r ${S}/ax3005/* ${D}${datadir}/axiado
}

do_deploy:append() {
# image-bundle.bin is deployed by caliptra-sw recipe
    install -m 0644 ${S}/ax3005/images/sbl.bin ${DEPLOYDIR}
    install -m 0644 ${S}/ax3005/images/secmc.img.ebin ${DEPLOYDIR}
# sysmgr.bin is deployed by irot recipe
}
