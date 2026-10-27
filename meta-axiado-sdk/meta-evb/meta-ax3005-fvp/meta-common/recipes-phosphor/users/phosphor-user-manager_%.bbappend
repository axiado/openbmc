FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-Add-new-group-for-Redfish-bootstrap-account.patch"

inherit useradd

GROUPADD_PACKAGES:append = " ${PN}"
GROUPADD_PARAM:${PN}:append = " ; -r redfishhi "

