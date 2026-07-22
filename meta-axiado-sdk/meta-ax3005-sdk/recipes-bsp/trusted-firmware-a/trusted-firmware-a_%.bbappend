LIC_FILES_CHKSUM = "file://docs/license.rst;md5=1118e32884721c0be33267bd7ae11130"

SRCREV_tfa = "17979655c872ef4de437f6511f6fcce866f9555e"
SRCBRANCH = "release/release-u-0.2.0"
SRC_URI_TRUSTED_FIRMWARE_A = "git://bitbucket.org/ax-engg/trusted-firmware-a.git;protocol=https"

# Remove patch for upstream repo lts-v2.14.1 because our repo based on v2.13-rc0
SRC_URI:remove = "file://0001-feat-build-add-HOSTLDFLAGS-to-pass-flags-to-host-lin.patch"

DEPENDS += "ax-bsp-headers"

TFA_PLATFORM = "ax3005"
TFA_DEBUG = "1"
TFA_SPD = "opteed"
TFA_BUILD_TARGET = "bl31"

EXTRA_OEMAKE += "LOG_LEVEL=40"

unset do_configure[noexec]

do_configure() {
    cp ${RECIPE_SYSROOT}/${includedir}/ax-bsp-headers/soc_memory_map_b0.h ${S}/plat/axiado/ax3005/include/
}

addtask do_configure before do_compile
