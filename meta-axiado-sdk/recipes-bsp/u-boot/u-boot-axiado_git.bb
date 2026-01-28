require u-boot-axiado.inc

SRCREV ?= "4e968b2f3747d543495c9601f8ab5e546f7542aa"

do_compile:append() {
    ${B}/tools/mkenvimage -s ${UBOOT_ENV_SIZE} -o ${B}/${UBOOT_ENV_BINARY} ${B}/u-boot-initial-env
}

PV = "2022.01+git${SRCPV}"
