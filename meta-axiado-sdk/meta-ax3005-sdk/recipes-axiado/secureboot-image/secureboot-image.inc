SUMMARY = "Axiado flash image tool"
DESCRIPTION = "Recipe to generate flash image for ax3005 SoC"

LICENSE = "CLOSED"

inherit deploy image_version

DEPENDS += "u-boot virtual/kernel linux-yocto-fitimage bmc-pb caliptra-image-tool-native python3-build-native pldm"

SRC_URI = "file://flash.json"

S = "${UNPACKDIR}"

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

        cp ${RECIPE_SYSROOT}${datadir}/axiado/images/caliptra.bin ${S}/images/
        cp ${RECIPE_SYSROOT}${datadir}/axiado/images/caliptra-auth.bin ${S}/images/
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

do_generate_signatures() {
    :
}

do_compile() {
    # Generate flash.bin
    AxImgGen ax-flash-image -c ax-flash.toml -w ${S}
}

do_generate_fwpkg[depends] += "pldm:do_populate_sysroot"
python do_generate_fwpkg() {
    import json
    import subprocess

    version_id = do_get_version(d)

    flash_json = d.expand("${UNPACKDIR}/flash.json")

    with open(flash_json, "r") as f:
        data = json.load(f)

    data["ComponentImageInformationArea"][0]["ComponentVersionString"] = version_id

    with open(flash_json, "w") as f:
        json.dump(data, f, indent=4)

    cmd = [
        "python3",
        d.expand("${RECIPE_SYSROOT}${datadir}/pldm/pldm_fwup_pkg_creator.py"),
        d.expand("${B}/flash.fwpkg"),
        d.expand("${UNPACKDIR}/flash.json"),
        d.expand("${S}/artifacts/flash.bin"),
    ]
    subprocess.check_call(cmd)
}

do_deploy() {
    install -m 0644 ${S}/artifacts/flash.bin ${DEPLOYDIR}/axiado-flash-${MACHINE}.bin
    install -m 0644 ${B}/flash.fwpkg ${DEPLOYDIR}/axiado-flash-${MACHINE}.fwpkg
}

addtask do_prepare_keys after do_configure
addtask do_generate_digest after do_prepare_keys
addtask do_generate_signatures after do_generate_digest
addtask do_compile after do_generate_signatures
addtask do_generate_fwpkg after do_compile
addtask do_deploy after do_generate_fwpkg before do_build
