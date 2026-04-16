require recipes-bsp/u-boot/u-boot-axiado.inc

SRCBRANCH = "${LATEST_RELEASE_VERSION}"
SRC_URI = "git://git@sourcevault.axiadord:7999/linux/u-boot.git;protocol=ssh;branch=${SRCBRANCH}"
SRCREV = "82176f3d5cc499666351934d1533ce8507e438a1"

do_compile:append() {
    ${B}/tools/mkenvimage -s ${UBOOT_ENV_SIZE} -o ${B}/${UBOOT_ENV_BINARY} ${B}/u-boot-initial-env
}

PV = "2022.04+git${SRCPV}"
