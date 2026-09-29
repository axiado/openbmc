require recipes-bsp/u-boot/u-boot-axiado.inc

# BU2A-559
SRCREV = "921f7a1a63198c76ab7ddc7ae433d5692b3580b8"
SRCBRANCH = "release/release-u-0.2.0"
SRC_URI = "git://bitbucket.org/ax-engg/u-boot_ultra.git;protocol=https;branch=${SRCBRANCH}"

DEPENDS += "ax-bsp-headers"

do_configure:prepend() {
    cp ${RECIPE_SYSROOT}/${includedir}/ax-bsp-headers/soc_memory_map_b0.h ${S}/include/configs/
    cp ${RECIPE_SYSROOT}/${includedir}/ax-bsp-headers/flash_map_ultra.h ${S}/include/configs/
}

PV = "2026.04+dev"
