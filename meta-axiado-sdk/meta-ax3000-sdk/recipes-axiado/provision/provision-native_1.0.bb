require provision.inc

PV = "1.0+dev"

SRCREV = "1b3a809d87de4b8bc88dd6434b0f00f9a20414e6"
SRCBRANCH = "release/release-0.18.0"
SRC_URI = "git://bitbucket.org/ax-engg/provision.git;protocol=https;branch=${SRCBRANCH}"
SRC_URI += "file://sign.txt"
# Make sure the mapping.txt is up-to-date
SRC_URI += "file://mapping.txt"
