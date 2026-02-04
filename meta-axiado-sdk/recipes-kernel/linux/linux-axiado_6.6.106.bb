SRCBRANCH = "dev-6.6-axiado"
SRCREV = "cd29f6df6909b29a6fd7aeab30096fd237395931"
KSRC = "git://github.com/axiado/linux-axiado;protocol=https;branch=${SRCBRANCH}"
SRC_URI += "file://kernel.scc \
            file://kernel.cfg \
            "

LINUX_VERSION ?= "6.6.106"

KBUILD_DEFCONFIG ?= "ax3000_defconfig"

require linux-axiado.inc
