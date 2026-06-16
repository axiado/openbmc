# Copyright (c) 2021-26 Axiado Corporation (or its affiliates). All rights reserved.

SUMMARY = "Provisioning tool"
DESCRIPTION = "A tool to add Axiado signature"
LICENSE = "CLOSED"

LIC_FILES_CHKSUM ?= "file://${COREBASE}/meta-axiado-sdk/COPYING.axiado;md5=3da9cfbcb788c80a0384361b4de20420"

inherit native

DEPENDS += "u-boot virtual/kernel linux-yocto-fitimage python3-build-native"

do_configure[depends] += "virtual/bootloader:do_deploy virtual/kernel:do_deploy linux-yocto-fitimage:do_deploy"
do_configure[nostamp] = "1"
do_compile[depends] += "virtual/bootloader:do_deploy virtual/kernel:do_deploy linux-yocto-fitimage:do_deploy"
do_compile[nostamp] = "1"

PROVISION_UBOOT ?= "FLASH_UBOOT.bin"
PROVISION_KERNEL_FILE ?= "fitImage"
PROVISION_SUFFIX ?= "asi"
PROVISION_KERNEL ?= "${PROVISION_KERNEL_FILE}.${PROVISION_SUFFIX}"

PROVISION_FLASH ?= "provision flash"
PROVISION_FLASH_ARGS ?= "-d -ds jenkins"

PROVISION_SCU ?= "provision flash_scu"
PROVISION_SCU_ARGS ?= "-i flash.yaml -s dev --update-image --sign-images -ui ${PROVISION_UBOOT}"

PROVISION_SIGNER ?= "provision signer"
PROVISION_SIGNER_ARGS ?= "-i ${B}/ws/meta-data.yaml -f ${B}/ws/images -l ${B}/ws/images/sign.txt -s dev -k single -m"

do_configure() {
    if [ -d "${B}/ws" ]; then
        rm -r ${B}/ws
    fi
    export TOOLS_HOME=${B}
    cd ${S}

    python3 -m tools.tooling ${PROVISION_FLASH} ${PROVISION_FLASH_ARGS}
    install -d ${B}/ws/images/updates
    install -m 0644 ${UNPACKDIR}/mapping.txt ${B}/ws/images/
    install -m 0644 ${UNPACKDIR}/sign.txt ${B}/ws/images/
    install -m 0644 ${DEPLOY_DIR_IMAGE}/u-boot.bin ${B}/ws/images/updates/uboot.bin

    if [ ! -L "${DEPLOY_DIR_IMAGE}/${PROVISION_KERNEL_FILE}" ]; then
        install -m 0644 ${DEPLOY_DIR_IMAGE}/${PROVISION_KERNEL_FILE} ${B}/ws/images/fitImage
    fi
}

do_compile() {
    export TOOLS_HOME=${B}
    cd ${S}

    python3 -m tools.tooling ${PROVISION_SCU} ${PROVISION_SCU_ARGS}

    if [ ! -L "${DEPLOY_DIR_IMAGE}/${PROVISION_KERNEL_FILE}" ]; then
        python3 -m tools.tooling ${PROVISION_SIGNER} ${PROVISION_SIGNER_ARGS}
    fi
}

do_deploy() {
    install -d ${DEPLOY_DIR_IMAGE}/axiado
    install -m 0644 ${B}/ws/artifacts/${PROVISION_UBOOT} ${DEPLOY_DIR_IMAGE}/axiado/

    if [ ! -L "${DEPLOY_DIR_IMAGE}/${PROVISION_KERNEL_FILE}" ]; then
        install -m 0644 ${B}/ws/images/fitImage.${PROVISION_SUFFIX} ${DEPLOY_DIR_IMAGE}/axiado/${PROVISION_KERNEL}
        ln -sf ${DEPLOY_DIR_IMAGE}/axiado/${PROVISION_KERNEL} ${DEPLOY_DIR_IMAGE}/${PROVISION_KERNEL_FILE}
    fi
}

addtask do_deploy after do_compile
