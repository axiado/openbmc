SUMMARY = "Axiado flash image tool"
DESCRIPTION = "Recipe to generate flash image for ax3005 SoC"

LICENSE = "CLOSED"

inherit deploy

DEPENDS += "u-boot virtual/kernel linux-yocto-fitimage bmc-pb caliptra-image-tool-native"
DEPENDS += '${@oe.utils.conditional("USE_CALIPTRA_DUMMY_KEYS", "1", "crypto-tool-native", "", d)}'

do_configure[dirs] = "${S}/images"
do_configure[depends] += "bmc-pb:do_populate_sysroot \
                          virtual/kernel:do_deploy \
                          linux-yocto-fitimage:do_deploy \
                          u-boot:do_deploy \
                          obmc-phosphor-image:do_image_complete \
                          "
do_configure() {
    # Axiado pre-built binaries
    if [ "${USE_CALIPTRA_DUMMY_KEYS}" = "1" ]; then
        bbwarn "Using dummy keys to generate flash image"

        cp ${RECIPE_SYSROOT}${datadir}/axiado/images/caliptra-02062026_170655.bin ${S}/images/
        cp ${RECIPE_SYSROOT}${datadir}/axiado/images/caliptra-auth-02062026_170655.bin ${S}/images/
    fi

    cp ${RECIPE_SYSROOT}${datadir}/axiado/images/image-bundle.bin ${S}/images/
    cp ${RECIPE_SYSROOT}${datadir}/axiado/images/sbl.bin ${S}/images/
    cp ${RECIPE_SYSROOT}${datadir}/axiado/images/secmc.img.ebin ${S}/images/
    cp ${RECIPE_SYSROOT}${datadir}/axiado/images/sysmgr.bin ${S}/images/

    # BMC images
    cp ${DEPLOY_DIR_IMAGE}/u-boot.bin ${S}/images/
    cp ${DEPLOY_DIR_IMAGE}/fitImage ${S}/images/
    cp ${DEPLOY_DIR_IMAGE}/obmc-phosphor-image-${MACHINE}.squashfs-xz ${S}/images/rootfs.bin
}

do_prepare_keys[dirs] = "${S}/keys \
                         ${S}/offline-keys/ecc \
                         ${S}/offline-keys/lms \
                         "
do_prepare_keys() {
    if [ "${USE_CALIPTRA_DUMMY_KEYS}" = "1" ]; then
        # Configurations
        cp ${RECIPE_SYSROOT}${datadir}/axiado/*.toml ${S}/
    fi
}

do_generate_digest[depends] += "caliptra-image-tool-native:do_populate_sysroot"
do_generate_digest() {
    # Generate digest
    AxImgGen ax-flash-image -c ax-flash.toml -s -w ${S}
}

do_generate_signatures[depends] += '${@oe.utils.conditional("USE_CALIPTRA_DUMMY_KEYS", "1", "crypto-tool-native:do_populate_sysroot", "", d)}'
do_generate_signatures() {
    :
}

do_compile() {
    # Generate flash.bin
    AxImgGen ax-flash-image -c ax-flash.toml -w ${S}
}

do_deploy() {
    install -m 0644 ${S}/artifacts/flash.bin ${DEPLOYDIR}/axiado-flash-${MACHINE}.bin
}

addtask do_prepare_keys after do_configure
addtask do_generate_digest after do_prepare_keys
addtask do_generate_signatures after do_generate_digest
addtask do_compile after do_generate_signatures
addtask do_deploy after do_compile before do_build
