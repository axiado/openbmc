FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = "git://git.gitlab.arm.com/server_management/pldm.git;branch=fvp_devel;protocol=https"
SRCREV = "bd852d3825077b74b85c86b29d4f8c75df016736"

PACKAGECONFIG[oem-arm] = "-Doem-arm=enabled, -Doem-arm=disabled"

