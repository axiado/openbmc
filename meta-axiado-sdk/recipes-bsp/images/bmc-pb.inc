SUMMARY = "Axiado Pre-built binaries for Axiado SoC family"

LICENSE = "CLOSED"

SRCREV = "72f4981f4a6cbffe0c0fed6093f3ecc4de8f3dae"
SRC_URI = "git://git@github.com/axiado/bmc_pb;protocol=ssh;branch=develop"

S = "${WORKDIR}/git"

inherit deploy

do_compile[noexec] = "1"

do_install() {
    install -d ${D}${datadir}/axiado
}

do_deploy () {
    install -d ${DEPLOYDIR}
}

FILES:${PN} = "${datadir}/axiado"

addtask do_deploy before do_build after do_install
