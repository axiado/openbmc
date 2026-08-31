FILESEXTRAPATHS:append := ":${THISDIR}/files"

SRC_URI += "file://0001-Add-new-type-MCTPMailboxTarget-into-pldmd.patch \
            file://0002-Add-back-maximum-transfer-size-configuration.patch \
            "

EXTRA_OEMESON += "-Dmaximum-transfer-size=1048567"

do_install:append() {
    install -d ${D}${datadir}/pldm/
    install -m 0755 ${S}/tools/fw-update/pldm_fwup_pkg_creator.py ${D}${datadir}/pldm/
}
