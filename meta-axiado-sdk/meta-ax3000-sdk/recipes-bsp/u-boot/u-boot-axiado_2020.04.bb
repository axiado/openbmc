require recipes-bsp/u-boot/u-boot-axiado.inc

SRCREV = "82176f3d5cc499666351934d1533ce8507e438a1"
SRCBRANCH = "release/release-0.18.0"
SRC_URI = "git://bitbucket.org/ax-engg/u-boot.git;protocol=https;branch=${SRCBRANCH}"

UBOOT_ENV_SRC = "build/u-boot-initial-env"

do_compile:append() {
    ${B}/tools/mkenvimage -s ${UBOOT_ENV_SIZE} -o ${B}/${UBOOT_ENV_BINARY} ${B}/u-boot-initial-env
}

PV = "2020.04+dev"
