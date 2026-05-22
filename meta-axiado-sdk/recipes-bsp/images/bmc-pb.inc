SUMMARY = "Axiado Pre-built binaries for Axiado SoC family"

LICENSE = "CLOSED"

SRCREV = "3a8dc26fc606008260aaf4723d44411f3753ab25"
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
