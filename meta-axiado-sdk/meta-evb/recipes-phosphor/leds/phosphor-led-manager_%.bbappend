FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://led-group-config.json"

do_install:append() {
    install -d ${D}${datadir}/phosphor-led-manager
    install -m 0644 ${UNPACKDIR}/led-group-config.json \
        ${D}${datadir}/phosphor-led-manager/led-group-config.json
}

