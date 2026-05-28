require secureboot-image.inc

do_configure[depends] += "caliptra-sw:do_populate_sysroot"
do_configure:append() {
    cp ${RECIPE_SYSROOT}${nonarch_base_libdir}/firmware/caliptra/image-bundle.bin ${S}/images/
}

PV = "1.0+dev"
