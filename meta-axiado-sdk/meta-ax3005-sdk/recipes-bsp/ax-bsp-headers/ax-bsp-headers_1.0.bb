SUMMARY = "Header file for ax3005 flash map layout"
DESCRIPTION = "Provides shared header files used by other recipes"
LICENSE = "CLOSED"

PV = "1.0+dev"

SRCBRANCH = "release/release-u-0.1.0"
SRC_URI += "git://bitbucket.org/ax-engg/ax-bsp-headers.git;protocol=https;branch=${SRCBRANCH}"
SRCREV = "7348dfbb8c8620bc00a65e47c76f90b7e615d6f3"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${includedir}/${PN}
    install -m 0644 ${S}/*.h ${D}${includedir}/${PN}/
}

FILES:${PN} += "${includedir}/${PN}"
