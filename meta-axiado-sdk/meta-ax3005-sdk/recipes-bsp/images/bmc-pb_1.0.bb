require recipes-bsp/images/bmc-pb.inc

SRCREV = "7b7e64665c32dae6bb3ccc9400e222ad47855a5f"
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
    install -m 0644 ${S}/ax3005/images/image-bundle.bin ${DEPLOYDIR}
    install -m 0644 ${S}/ax3005/images/sbl.bin ${DEPLOYDIR}
    install -m 0644 ${S}/ax3005/images/secmc.img.ebin ${DEPLOYDIR}
    install -m 0644 ${S}/ax3005/images/sysmgr.bin ${DEPLOYDIR}
}
