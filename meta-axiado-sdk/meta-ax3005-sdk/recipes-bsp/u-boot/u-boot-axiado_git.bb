require recipes-bsp/u-boot/u-boot-axiado.inc

SRCBRANCH = "master"
SRC_URI = "git://github.com/axiado/u-boot-ax3005;protocol=https;branch=${SRCBRANCH}"
SRCREV = "f0c0d84d7d93d23a808721b22482bef153ca2c9f"

UBOOT_ENV_SRC = "build/u-boot-initial-env"

do_compile:append() {
    ${B}/tools/mkenvimage -s ${UBOOT_ENV_SIZE} -o ${B}/${UBOOT_ENV_BINARY} ${B}/u-boot-initial-env
}

PV = "2026.04+git"
