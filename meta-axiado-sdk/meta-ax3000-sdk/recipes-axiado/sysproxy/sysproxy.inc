# Copyright (c) 2021-26 Axiado Corporation (or its affiliates). All rights reserved.

SUMMARY = "Sysproxy"
SECTION = "Sysproxy application"
LICENSE = "CLOSED"

LIC_FILES_CHKSUM ?= "file://COPYING.axiado;md5=01d0d9bdb04606d39dcbff1ca352f133"

REL_BUILD_ID = "${@int(os.getenv('REL_BUILD_ID', '0'))}"

inherit obmc-phosphor-systemd cmake pkgconfig

ERROR_QA:remove = "buildpaths"
WARNNING_QA:append = "buildpaths"

do_configure:prepend() {
        cp -r ${UNPACKDIR}/sdk ${WORKDIR}/
        cp -r ${UNPACKDIR}/ax_hal ${WORKDIR}/
        cp -r ${UNPACKDIR}/qpc ${WORKDIR}/
        cp -r ${UNPACKDIR}/porting ${WORKDIR}/
}

do_compile() {
        rm -rf ${S}/build ${WORKDIR}/sdk/build ${WORKDIR}/ax_hal/build
        cd ${B}
        cmake ${S} -DREL_BUILD_ID=${REL_BUILD_ID} -DCMAKE_TRY_COMPILE_TARGET_TYPE=STATIC_LIBRARY
        cd ${S}
        cmake --build ${B}
}

do_install() {
        install -d ${D}${bindir}
        install -m 0755 ${B}/sysmgr_proxy ${D}${bindir}
}
