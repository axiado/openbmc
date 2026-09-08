require provision.inc

PV = "1.0+dev"

SRCREV = "15fc17c0685e132186218ba0d170c11edfffe5b6"
SRCBRANCH = "release/release-0.18.0"
SRC_URI = "git://bitbucket.org/ax-engg/provision.git;protocol=https;branch=${SRCBRANCH}"
SRC_URI += "file://sign.txt"
# Make sure the mapping.txt is up-to-date
SRC_URI += "file://mapping.txt"
