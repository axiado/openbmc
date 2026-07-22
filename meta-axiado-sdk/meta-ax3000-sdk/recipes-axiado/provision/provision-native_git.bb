require provision.inc

PV = "1.0+git"

SRCREV = "6d36fe75f73cba8802f1a7edabfe40704df6211a"
SRC_URI = "git://git@github.com/axiado/kirkwood-image-tool;protocol=ssh;branch=develop"
SRC_URI += "file://sign.txt"
# Make sure the mapping.txt is up-to-date
SRC_URI += "file://mapping.txt"

PROVISION_FLASH = "imaging flash"
PROVISION_FLASH_ARGS = "-d -ds bootstraps"

PROVISION_SCU = "imaging flash_scu"
PROVISION_SCU_ARGS = "-i flash.yaml -s dev -k single --update-image --sign-images -ui ${PROVISION_UBOOT}"

PROVISION_SIGNER = "imaging signer"
PROVISION_SIGNER_ARGS = "-i ${B}/ws/meta_data.yaml -f ${B}/ws/images -l ${B}/ws/images/sign.txt -s dev -k single -m"
