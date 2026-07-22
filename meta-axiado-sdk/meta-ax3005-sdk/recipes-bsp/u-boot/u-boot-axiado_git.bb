require recipes-bsp/u-boot/u-boot-axiado.inc

SRCBRANCH = "master"
SRC_URI = "git://github.com/axiado/u-boot-ax3005;protocol=https;branch=${SRCBRANCH}"
SRCREV = "1ba4cd2c115767b5ad7df0b35a8f9340bc7d6269"

UBOOT_ENV_SRC = "build/u-boot-initial-env"

do_compile:append() {
    ${B}/tools/mkenvimage -s ${UBOOT_ENV_SIZE} -o ${B}/${UBOOT_ENV_BINARY} ${B}/u-boot-initial-env
}

PV = "2026.04+git"
