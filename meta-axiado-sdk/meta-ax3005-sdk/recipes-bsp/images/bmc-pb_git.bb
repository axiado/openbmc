require recipes-bsp/images/bmc-pb.inc

# BU2-553
SRCREV = "257401106429bcb3effa93032fec575b09e714e6"
SRCBRANCH = "master"
SRC_URI = "git://git@github.com/axiado/bmc_pb;protocol=ssh;branch=${SRCBRANCH}"
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
    install -m 0644 -D ${S}/ax3005/images/bl31.bin ${DEPLOYDIR}/trusted-firmware-a/bl31.bin
    install -m 0644 -D ${S}/ax3005/images/tee-pager_v2.bin ${DEPLOYDIR}/optee/tee-pager_v2.bin
}
