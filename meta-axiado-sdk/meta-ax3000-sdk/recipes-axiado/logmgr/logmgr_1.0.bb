require recipes-axiado/logmgr/logmgr.inc

PV = "1.0"

SRCBRANCH = "${LATEST_RELEASE_VERSION}"
SRCREV = "3d39ccb18da019472f35e184ccaefa35625c07be"
SRC_URI = "git://git@sourcevault.axiadord:7999/apps/logmgr.git;protocol=ssh;branch=${SRCBRANCH}"
