require recipes-bsp/u-boot/u-boot-axiado.inc

SRCREV = "f9aff2b12e89b64817034fa8740df68030298974"
SRCBRANCH = "release/release-u-0.2.0"
SRC_URI = "git://bitbucket.org/ax-engg/u-boot_ultra.git;protocol=https;branch=${SRCBRANCH}"

DEPENDS += "ax-bsp-headers"

do_configure:prepend() {
    cp ${RECIPE_SYSROOT}/${includedir}/ax-bsp-headers/soc_memory_map_b0.h ${S}/include/configs/
}

do_compile:append() {
    ${B}/tools/mkenvimage -s ${UBOOT_ENV_SIZE} -o ${B}/${UBOOT_ENV_BINARY} ${B}/u-boot-initial-env
}

PV = "2026.04+dev"
