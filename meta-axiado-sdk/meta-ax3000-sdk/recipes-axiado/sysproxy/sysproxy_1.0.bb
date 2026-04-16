require recipes-axiado/sysproxy/sysproxy.inc

PV = "1.0"

SRCBRANCH = "${LATEST_RELEASE_VERSION}"
SRCBRANCH_qpc = "release/release-7.1.0"
SRCREV_FORMAT = "sysproxy_axhal_sdk_porting_qpc"
SRCREV_sysproxy = "0faae786ed338c929baabecbc64e823d10d9bac2"
SRCREV_axhal = "d16c43a0bec3495b5e410fe58df68abd4de73791"
SRCREV_sdk = "a608fb08cb077b1028782360e52dc28590ef56c6"
SRCREV_porting = "9457344ecd2fe7817d02cb717a75e5e3187b8553"
SRCREV_qpc = "2f827e8203df2fc613f6ab852b0dd8d081f6a432"
SRC_URI = "git://git@sourcevault.axiadord:7999/apps/sysproxy.git;protocol=ssh;branch=${SRCBRANCH};name=sysproxy \
           git://git@sourcevault.axiadord:7999/axsw/ax_hal.git;protocol=ssh;branch=${SRCBRANCH};name=axhal;destsuffix=ax_hal \
           git://git@sourcevault.axiadord:7999/axsw/sdk.git;protocol=ssh;branch=${SRCBRANCH};name=sdk;destsuffix=sdk \
           git://git@sourcevault.axiadord:7999/axsw/porting.git;protocol=ssh;branch=${SRCBRANCH};name=porting;destsuffix=porting \
           git://git@sourcevault.axiadord:7999/ext/qpc.git;protocol=ssh;branch=${SRCBRANCH_qpc};name=qpc;destsuffix=qpc \
           "

SRC_URI += "file://sysproxy.service"

do_install:append() {
        install -d ${D}/${systemd_unitdir}/system
        install -m 0644 ${UNPACKDIR}/sysproxy.service ${D}/${systemd_unitdir}/system
}
