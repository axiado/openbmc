LIC_FILES_CHKSUM = "file://docs/license.rst;md5=1118e32884721c0be33267bd7ae11130"

SRCREV_FORMAT:append = "_ax-bsp-headers"
SRCREV_tfa = "17979655c872ef4de437f6511f6fcce866f9555e"
SRCREV_ax-bsp-headers = "1736e94ee2e94f2b09665d5a0c3bd8f3b50519a3"
SRCBRANCH = "release/release-u-0.1.0"
SRC_URI_TRUSTED_FIRMWARE_A = "git://bitbucket.org/ax-engg/trusted-firmware-a.git;protocol=https"
SRC_URI += "git://bitbucket.org/ax-engg/ax-bsp-headers.git;protocol=https;branch=${SRCBRANCH};name=ax-bsp-headers;destsuffix=ax-bsp-headers"

# Remove patch for upstream repo v2.12 because our repo v2.14 has this fix already
SRC_URI:remove = "file://0001-qemu_measured_boot.c-ignore-TPM-error-and-continue-w.patch"

TFA_PLATFORM = "ax3005"
TFA_DEBUG = "1"
TFA_SPD = "opteed"
TFA_BUILD_TARGET = "bl31"

EXTRA_OEMAKE += "LOG_LEVEL=40"

unset do_configure[noexec]

do_configure() {
    cp ${UNPACKDIR}/ax-bsp-headers/soc_memory_map_b0.h ${S}/plat/axiado/ax3005/include/
}

addtask do_configure before do_compile
