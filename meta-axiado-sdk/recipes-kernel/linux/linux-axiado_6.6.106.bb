SRCBRANCH = "dev-6.6-axiado"
SRCREV = "7013fbeda183db2617fe33d9392f259f8045de13"
KSRC = "git://github.com/axiado/linux-axiado;protocol=https;branch=${SRCBRANCH}"
SRC_URI += "file://kernel.scc \
            file://kernel.cfg \
            "

LINUX_VERSION ?= "6.6.106"

KBUILD_DEFCONFIG ?= "ax3000_defconfig"

require linux-axiado.inc
