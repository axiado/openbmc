FILESEXTRAPATHS:append := "${THISDIR}/files:"

SRCREV_FORMAT = "optee-os_ax-bsp-headers"
SRCREV_optee-os = "da3d27c73a4303a9f21a740e0fcb6a1fbfd22d61"
SRCREV_ax-bsp-headers = "1736e94ee2e94f2b09665d5a0c3bd8f3b50519a3"
SRCBRANCH = "release/release-u-0.1.0"
# Upstream OpenBMC is using v4.4 but Axiado is using v4.10
SRC_URI = "git://bitbucket.org/ax-engg/optee_os.git;protocol=https;branch=${SRCBRANCH};name=optee-os"
SRC_URI += "git://bitbucket.org/ax-engg/ax-bsp-headers.git;protocol=https;branch=${SRCBRANCH};name=ax-bsp-headers;destsuffix=ax-bsp-headers"

OPTEEMACHINE = "axiado-ax3005"

do_configure:prepend() {
    cp ${UNPACKDIR}/ax-bsp-headers/soc_memory_map_b0.h ${S}/core/arch/arm/plat-axiado/
}
