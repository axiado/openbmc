FILESEXTRAPATHS:append := "${THISDIR}/files:"

DEPENDS += "ax-bsp-headers"

SRCREV = "da3d27c73a4303a9f21a740e0fcb6a1fbfd22d61"
SRCBRANCH = "release/release-u-0.1.0"
# Upstream OpenBMC is using v4.4 but Axiado is using v4.10
SRC_URI = "git://bitbucket.org/ax-engg/optee_os.git;protocol=https;branch=${SRCBRANCH};name=optee-os"

OPTEEMACHINE = "axiado-ax3005"

do_configure:prepend() {
    cp ${RECIPE_SYSROOT}/${includedir}/ax-bsp-headers/soc_memory_map_b0.h ${S}/core/arch/arm/plat-axiado/
}
