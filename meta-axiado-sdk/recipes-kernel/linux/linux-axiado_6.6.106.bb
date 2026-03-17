SRCBRANCH = "dev-6.6-axiado"
SRCREV = "8b3d523a4e86af740975134247c619b8dfad140d"
KSRC = "git://github.com/axiado/linux-axiado;protocol=https;branch=${SRCBRANCH}"
SRC_URI += "file://kernel.scc \
            file://kernel.cfg \
            "

LINUX_VERSION ?= "6.6.106"

KBUILD_DEFCONFIG ?= "ax3000_defconfig"

require linux-axiado.inc
