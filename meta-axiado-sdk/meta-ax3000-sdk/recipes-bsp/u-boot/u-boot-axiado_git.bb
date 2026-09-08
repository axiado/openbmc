require recipes-bsp/u-boot/u-boot-axiado.inc

DEPENDS += "bc-native"

SRCREV = "b08355f003675f0ed0043af40b20645b2439f7a8"
SRCBRANCH = "u-boot-2019.04-axiado"
SRC_URI = "git://github.com/axiado/u-boot-axiado;protocol=https;branch=${SRCBRANCH}"
SRC_URI += "file://u-boot-axiado-env"

# 2019.04 does not have u-boot-initial-env target to build
UBOOT_INITIAL_ENV = ""

UBOOT_ENV_BINARY = "u-boot-axiado-initial-env.bin"

do_compile:append() {
    ${B}/tools/mkenvimage -s ${UBOOT_INITIAL_ENV_BINARY_SIZE} -o ${B}/${UBOOT_ENV_BINARY} ${UNPACKDIR}/u-boot-axiado-env
}

do_deploy:append() {
    install -D -m 644 ${B}/${UBOOT_ENV_BINARY} ${DEPLOYDIR}/${UBOOT_ENV_BINARY}
}

PV = "2019.04+git"
