require recipes-bsp/u-boot/u-boot-axiado.inc

SRC_URI = "git://bitbucket.org/ax-engg/u-boot_ultra.git;protocol=https;branch=release/release-u-0.2.0;name=u-boot-ultra"
SRC_URI += "git://bitbucket.org/ax-engg/ax-bsp-headers.git;protocol=https;branch=release/release-u-0.1.0;name=ax-bsp-headers;destsuffix=ax-bsp-headers"

SRCREV_FORMAT = "u-boot-ultra_ax-bsp-headers"
SRCREV_u-boot-ultra ?= "d40020c59e24f953f89dc99131a8038a06b992f1"
SRCREV_ax-bsp-headers ?= "dd74eddd0ba9eb287e9ef1f24ca2c556c44c7763"

do_configure:prepend() {
        cp ${UNPACKDIR}/ax-bsp-headers/soc_memory_map_b0.h ${S}/include/configs/
}

do_compile:append() {
    ${B}/tools/mkenvimage -s ${UBOOT_ENV_SIZE} -o ${B}/${UBOOT_ENV_BINARY} ${B}/u-boot-initial-env
}

PV = "2026.04"
