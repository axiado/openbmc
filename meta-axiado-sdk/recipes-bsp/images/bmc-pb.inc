SUMMARY = "Axiado Pre-built binaries for Axiado SoC family"

LICENSE = "CLOSED"

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
