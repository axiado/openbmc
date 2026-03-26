require sysproxy.inc

PV = "1.0"

SRCBRANCH = "${LATEST_RELEASE_VERSION}"
SRCBRANCH_qpc = "release/release-7.1.0"
SRCREV_FORMAT = "sysproxy_axhal_sdk_porting_qpc"
SRCREV_sysproxy = "0faae786ed338c929baabecbc64e823d10d9bac2"
SRCREV_axhal = "d16c43a0bec3495b5e410fe58df68abd4de73791"
SRCREV_sdk = "a608fb08cb077b1028782360e52dc28590ef56c6"
SRCREV_porting = "9457344ecd2fe7817d02cb717a75e5e3187b8553"
SRCREV_qpc = "2f827e8203df2fc613f6ab852b0dd8d081f6a432"
SRC_URI = "git://bitbucket.org/ax-engg/sysproxy.git;protocol=https;branch=${SRCBRANCH};name=sysproxy \
           git://bitbucket.org/ax-engg/ax_hal.git;protocol=https;branch=${SRCBRANCH};name=axhal;destsuffix=ax_hal \
           git://bitbucket.org/ax-engg/sdk.git;protocol=https;branch=${SRCBRANCH};name=sdk;destsuffix=sdk \
           git://bitbucket.org/ax-engg/porting.git;protocol=https;branch=${SRCBRANCH};name=porting;destsuffix=porting \
           git://bitbucket.org/ax-engg/qpc.git;protocol=https;branch=${SRCBRANCH_qpc};name=qpc;destsuffix=qpc \
           "

SRC_URI += "file://sysproxy.service"

do_install:append() {
        install -d ${D}/${systemd_unitdir}/system
        install -m 0644 ${UNPACKDIR}/sysproxy.service ${D}/${systemd_unitdir}/system
}
