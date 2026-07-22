FILESEXTRAPATHS:append := ":${THISDIR}/files"

SRC_URI += "file://0001-Add-new-type-MCTPMailboxTarget-into-pldmd.patch \
            file://0002-Increase-MAXIMUM_TRANSFER_SIZE-of-PLDM.patch \
            "

do_install:append() {
    install -d ${D}${datadir}/pldm/
    install -m 0755 ${S}/tools/fw-update/pldm_fwup_pkg_creator.py ${D}${datadir}/pldm/
}
