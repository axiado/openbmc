do_install:append() {
    install -d ${D}${datadir}/pldm/
    install -m 0755 ${S}/tools/fw-update/pldm_fwup_pkg_creator.py ${D}${datadir}/pldm/
}
