require recipes-bsp/u-boot/u-boot-axiado.inc

SRC_URI = "git://bitbucket.org/ax-engg/u-boot_ultra.git;protocol=https;branch=release/release-u-0.2.0;name=u-boot-ultra"
SRC_URI += "git://bitbucket.org/ax-engg/ax-bsp-headers.git;protocol=https;branch=release/release-u-0.1.0;name=ax-bsp-headers;destsuffix=ax-bsp-headers"

SRCREV_FORMAT = "u-boot-ultra_ax-bsp-headers"
SRCREV_u-boot-ultra ?= "e85a21ddfcb439688a98c01953cce898213a9a33"
SRCREV_ax-bsp-headers ?= "d2f252081e61e2c8eede4c03cd2115cf24e23be8"

do_configure:prepend() {
        cp ${UNPACKDIR}/ax-bsp-headers/soc_memory_map_b0.h ${S}/include/configs/
}

do_compile:append() {
    ${B}/tools/mkenvimage -s ${UBOOT_ENV_SIZE} -o ${B}/${UBOOT_ENV_BINARY} ${B}/u-boot-initial-env
}

PV = "2026.04+git${SRCPV}"
