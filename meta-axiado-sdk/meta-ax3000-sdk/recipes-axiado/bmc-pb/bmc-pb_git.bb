require recipes-bsp/images/bmc-pb.inc

do_install:append() {
    cp -r ${S}/ax3000/* ${D}${datadir}/axiado
}
