SUMMARY = "Header file for ax3005 flash map layout"
DESCRIPTION = "Provides shared header files used by other recipes"
LICENSE = "CLOSED"

PV = "1.0+dev"

# BU2-553
SRCREV = "d725372aad9fda78b2db79f8612f22fc3bb87d50"
SRCBRANCH = "release/release-u-0.2.0"
SRC_URI += "git://bitbucket.org/ax-engg/ax-bsp-headers.git;protocol=https;branch=${SRCBRANCH}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${includedir}/${PN}
    install -m 0644 ${S}/*.h ${D}${includedir}/${PN}/
}

FILES:${PN} += "${includedir}/${PN}"
