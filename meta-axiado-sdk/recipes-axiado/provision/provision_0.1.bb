# Copyright (c) 2021-26 Axiado Corporation (or its affiliates). All rights reserved.

SUMMARY = "Provisioning tool"
DESCRIPTION = "A tool to add Axiado signature"
LICENSE = "CLOSED"
PV = "0.1"

LIC_FILES_CHKSUM ?= "file://${COREBASE}/meta-axiado-sdk/COPYING.axiado;md5=3da9cfbcb788c80a0384361b4de20420"

SRCREV = "4bd8c408778e454e52cd85670c8f304fb0aa0422"
SRC_URI = "git://git@github.com/axiado/kirkwood-image-tool;protocol=ssh;branch=develop"
SRC_URI += "file://sign.txt"
# Make sure the mapping.txt is up-to-date
SRC_URI += "file://mapping.txt"

DEPENDS += "u-boot virtual/kernel python3-build-native"

do_configure[depends] += "virtual/bootloader:do_deploy virtual/kernel:do_deploy"
do_configure[nostamp] = "1"
do_compile[depends] += "virtual/bootloader:do_deploy virtual/kernel:do_deploy"
do_compile[nostamp] = "1"

S = "${WORKDIR}/git"

PROVISION_UBOOT = "FLASH_UBOOT.bin"
PROVISION_KERNEL_FILE = "fitImage"
PROVISION_SUFFIX = "asi"
PROVISION_KERNEL = "${PROVISION_KERNEL_FILE}.${PROVISION_SUFFIX}"

do_configure() {
    if [ -d "${B}/ws" ]; then
        rm -r ${B}/ws
    fi
    export TOOLS_HOME=${B}
    cd ${S}

    python3 -m tools.tooling imaging flash -d -ds bootstraps
    install -d ${B}/ws/images/updates
    install -m 0644 ${UNPACKDIR}/mapping.txt ${B}/ws/images/
    install -m 0644 ${UNPACKDIR}/sign.txt ${B}/ws/images/
    install -m 0644 ${DEPLOY_DIR_IMAGE}/u-boot.bin ${B}/ws/images/updates/uboot.bin

    filename=$(readlink ${DEPLOY_DIR_IMAGE}/${PROVISION_KERNEL_FILE})
    if [ "${filename##*.}" != "${PROVISION_SUFFIX}" ]; then
        install -m 0644 ${DEPLOY_DIR_IMAGE}/${PROVISION_KERNEL_FILE} ${B}/ws/images/fitImage
    fi
}

do_compile() {
    export TOOLS_HOME=${B}
    cd ${S}

    python3 -m tools.tooling imaging flash_scu -i flash.yaml -s dev -k single --update-image --sign-images -ui ${PROVISION_UBOOT}

    filename=$(readlink ${DEPLOY_DIR_IMAGE}/${PROVISION_KERNEL_FILE})
    if [ "${filename##*.}" != "${PROVISION_SUFFIX}" ]; then
        python3 -m tools.tooling imaging signer -i ${B}/ws/meta_data.yaml -f ${B}/ws/images -l ${B}/ws/images/sign.txt -s dev -k single -m
    fi
}

do_deploy() {
    install -d ${DEPLOY_DIR_IMAGE}/axiado
    install -m 0644 ${B}/ws/artifacts/${PROVISION_UBOOT} ${DEPLOY_DIR_IMAGE}/axiado/

    filename=$(readlink ${DEPLOY_DIR_IMAGE}/${PROVISION_KERNEL_FILE})
    if [ "${filename##*.}" != "${PROVISION_SUFFIX}" ]; then
        install -m 0644 ${B}/ws/images/fitImage.${PROVISION_SUFFIX} ${DEPLOY_DIR_IMAGE}/axiado/${PROVISION_KERNEL}
        ln -sf ${DEPLOY_DIR_IMAGE}/axiado/${PROVISION_KERNEL} ${DEPLOY_DIR_IMAGE}/${PROVISION_KERNEL_FILE}
    fi
}

addtask do_deploy after do_compile
