require recipes-axiado/provision/provision.inc

PV = "0.1"

SRCREV = "4fdfb389479b76631ebe6964c86a22aa15a6bd22"
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
