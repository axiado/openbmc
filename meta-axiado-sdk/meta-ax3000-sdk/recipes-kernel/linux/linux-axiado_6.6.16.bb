SRCREV = "d6b9398590e96a48682bdecc036e50bff0edc411"
SRCBRANCH = "${LATEST_RELEASE_VERSION}"
SRC_URI = "git://git@sourcevault.axiadord:7999/linux/kernel.git;protocol=ssh;branch=${SRCBRANCH}"

LINUX_VERSION = "6.6.16"

require recipes-kernel/linux/linux-axiado.inc
