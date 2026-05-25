SUMMARY = "Axiado Pre-built binaries for Axiado SoC family"

LICENSE = "CLOSED"

SRCREV = "84a31e920cc0b2ba69ec296fe7dd120755377545"
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
