require recipes-bsp/u-boot/u-boot-axiado.inc

SRCREV = "c7d16bfd34e53c2ccf628f5138587a5114c94cc5"
SRCBRANCH = "release/release-u-0.2.0"
SRC_URI = "git://bitbucket.org/ax-engg/u-boot_ultra.git;protocol=https;branch=${SRCBRANCH}"

DEPENDS += "ax-bsp-headers"

do_configure:prepend() {
    cp ${RECIPE_SYSROOT}/${includedir}/ax-bsp-headers/soc_memory_map_b0.h ${S}/include/configs/
    cp ${RECIPE_SYSROOT}/${includedir}/ax-bsp-headers/flash_map_ultra.h ${S}/include/configs/
}

PV = "2026.04+dev"
