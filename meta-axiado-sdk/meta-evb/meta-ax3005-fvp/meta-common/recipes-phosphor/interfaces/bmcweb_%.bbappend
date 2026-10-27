SRCREV = "8eda821874fc26aa22090124000cc61ba358bc01"
SRC_URI = "git://git.gitlab.arm.com/server_management/bmcweb.git;branch=fvp_devel;protocol=https"

FILESEXTRAPATHS:prepend := "${THISDIR}/bmcweb:"
SRC_URI += "file://0001-Fix-build-with-Boost-1.90-beast-fields.patch"
