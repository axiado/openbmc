require recipes-axiado/tdfu/tdfu.inc

PV = "1.0"

SRCBRANCH = "${LATEST_RELEASE_VERSION}"
SRCREV = "4693be8750640592275644351cb15eafde03f8c3"
SRC_URI = "git://git@sourcevault.axiadord:7999/apps/tdfu.git;protocol=ssh;branch=${SRCBRANCH}"
