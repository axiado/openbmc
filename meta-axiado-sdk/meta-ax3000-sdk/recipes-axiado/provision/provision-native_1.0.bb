require provision.inc

PV = "1.0+dev"

SRCREV = "edbae7583817b766e97b67beb5faa71a03689d5b"
SRCBRANCH = "release/release-0.18.0"
SRC_URI = "git://bitbucket.org/ax-engg/provision.git;protocol=https;branch=${SRCBRANCH}"
SRC_URI += "file://sign.txt"
# Make sure the mapping.txt is up-to-date
SRC_URI += "file://mapping.txt"
