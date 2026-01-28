# Copyright (c) 2021-24 Axiado Corporation (or its affiliates). All rights reserved.

SUMMARY = "Provisioning tool"
DESCRIPTION = "A tool to add Axiado signature"
LICENSE = "CLOSED"
PV = "1.0"

LIC_FILES_CHKSUM ?= "file://${COREBASE}/meta-axiado-sdk/COPYING.axiado;md5=3da9cfbcb788c80a0384361b4de20420"

SRCBRANCH ??= "${LATEST_RELEASE_VERSION}"
SRCREV = "c32abe0a10607bfe5e8db155cf0fc83213c36a36"
SRC_URI = "git://git@sourcevault.axiadord:7999/bit/provision.git;protocol=ssh;branch=${SRCBRANCH}"
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

    python3 -m tools.tooling provision flash -d -ds jenkins
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

    python3 -m tools.tooling provision flash_scu -i flash.yaml -s dev --update-image --sign-images -ui ${PROVISION_UBOOT}

    filename=$(readlink ${DEPLOY_DIR_IMAGE}/${PROVISION_KERNEL_FILE})
    if [ "${filename##*.}" != "${PROVISION_SUFFIX}" ]; then
        python3 -m tools.tooling provision signer -i ${B}/ws/meta-data.yaml -f ${B}/ws/images -l ${B}/ws/images/sign.txt -s dev -k single -m
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
