FILESEXTRAPATHS:append := "${THISDIR}/files:"

SRC_URI += "file://mctpmbox0.rules"

do_install:append() {
    install -d ${D}/${nonarch_base_libdir}/udev/rules.d
    install -m 0644 ${UNPACKDIR}/mctpmbox0.rules \
        ${D}${nonarch_base_libdir}/udev/rules.d/
}

#FILES:${PN} += "${sysconfdir}/udev/rules.d/mctpmbox0.rules"
