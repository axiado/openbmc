require secureboot-image.inc

DEPENDS += "caliptra-sw irot optee-os trusted-firmware-a"

do_configure[depends] += "caliptra-sw:do_populate_sysroot \
                          irot:do_populate_sysroot \
                          optee-os:do_populate_sysroot \
                          trusted-firmware-a:do_populate_sysroot \
                          "
do_configure:append() {
    cp ${RECIPE_SYSROOT}${nonarch_base_libdir}/firmware/caliptra/image-bundle.bin ${S}/images/
    cp ${RECIPE_SYSROOT}${datadir}/ax3005/sysmgr.bin ${S}/images/
}

PV = "1.0+dev"
