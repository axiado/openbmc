SUMMARY = "Linux tools for QA test"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

RDEPENDS:${PN} = "parted gptfdisk util-linux stress-ng"
RDEPENDS:${PN} += "glibc readline ncurses opkg-utils libgcc popt libstdc++ libbsd libmd"
