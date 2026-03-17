require u-boot-axiado.inc

SRCREV ?= "82176f3d5cc499666351934d1533ce8507e438a1"

do_compile:append() {
    ${B}/tools/mkenvimage -s ${UBOOT_ENV_SIZE} -o ${B}/${UBOOT_ENV_BINARY} ${B}/u-boot-initial-env
}

PV = "2022.01+git${SRCPV}"
