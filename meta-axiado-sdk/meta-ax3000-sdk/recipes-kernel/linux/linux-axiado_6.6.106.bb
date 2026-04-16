SRCBRANCH = "dev-6.6-axiado"
SRCREV = "bc62968a109cfdfa05025a2f58b4354a9a23a539"
SRC_URI = "git://github.com/axiado/linux-axiado;protocol=https;branch=${SRCBRANCH}"
SRC_URI += "file://kernel.scc \
            file://kernel.cfg \
            "

LINUX_VERSION = "6.6.106"

require recipes-kernel/linux/linux-axiado.inc
