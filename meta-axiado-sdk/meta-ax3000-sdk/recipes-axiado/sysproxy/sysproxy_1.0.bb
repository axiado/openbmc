require sysproxy.inc

PV = "1.0+dev"

SRCBRANCH = "release/release-0.18.0"
SRCBRANCH_qpc = "release/release-7.1.0"
SRCREV_FORMAT = "sysproxy_axhal_sdk_porting_qpc"
SRCREV_sysproxy = "49046f65f05aa7287106a7f1a7bf506093212aca"
SRCREV_axhal = "ca3d3ff777bff4cd09c60150d4c94eec9d9f7f53"
SRCREV_sdk = "bd81f0d4db67586fb0c696f2df02f4e7cf8435bd"
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
