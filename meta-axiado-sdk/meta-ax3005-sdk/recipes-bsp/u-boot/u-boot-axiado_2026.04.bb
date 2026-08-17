require recipes-bsp/u-boot/u-boot-axiado.inc

# BU2-553
SRCREV = "d651bf25497691edaa68e46f295bce07ffb06734"
SRCBRANCH = "release/release-u-0.2.0"
SRC_URI = "git://bitbucket.org/ax-engg/u-boot_ultra.git;protocol=https;branch=${SRCBRANCH}"

DEPENDS += "ax-bsp-headers"

do_configure:prepend() {
    cp ${RECIPE_SYSROOT}/${includedir}/ax-bsp-headers/soc_memory_map_b0.h ${S}/include/configs/
    cp ${RECIPE_SYSROOT}/${includedir}/ax-bsp-headers/flash_map_ultra.h ${S}/include/configs/
}

PV = "2026.04+dev"
