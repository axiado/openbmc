require recipes-bsp/images/bmc-pb.inc

SRCREV = "806d144a35447720638658f763dc8fabcc3f22e6"
SRC_URI = "git://bitbucket.org/ax-engg/ax-bsp-prebuild.git;protocol=https;branch=develop"
SRC_URI += "file://ax-images.toml"
# B528
SRC_URI += "file://sbl.bin"

PV = "1.0+dev"

do_configure() {
    install -m 0644 ${UNPACKDIR}/ax-images.toml ${S}/ax3005
    install -m 0644 ${UNPACKDIR}/sbl.bin ${S}/ax3005/images
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
