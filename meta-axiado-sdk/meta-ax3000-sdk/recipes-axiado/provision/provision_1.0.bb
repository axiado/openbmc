require provision.inc

PV = "1.0"

SRCBRANCH = "${LATEST_RELEASE_VERSION}"
SRCREV = "2ea515d1d482649ae8b6dab7bfec77dcebd23820"
SRC_URI = "git://bitbucket.org/ax-engg/provision.git;protocol=https;branch=${SRCBRANCH}"
SRC_URI += "file://sign.txt"
# Make sure the mapping.txt is up-to-date
SRC_URI += "file://mapping.txt"
