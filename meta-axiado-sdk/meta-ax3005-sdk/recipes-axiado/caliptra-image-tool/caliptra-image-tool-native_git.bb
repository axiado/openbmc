SUMMARY = "Axiado Caliptra flash image tool"
DESCRIPTION = "Axiado flash tool for Caliptra image generation"

LICENSE = "CLOSED"

SRCREV = "672f493630537f0172183ad9f89de055614d5946"
SRC_URI = "gitsm://git@github.com/axiado/caliptra_img_gen;protocol=ssh;branch=develop"

inherit cargo native

export RUSTFLAGS += " -C linker=${BUILD_CC}"

# Using cargo to download packages
CARGO_DISABLE_BITBAKE_VENDORING = "1"

# Enable network for the compile task allowing cargo to download dependencies
do_compile[network] = "1"

do_configure[noexec] = "1"

do_compile() {
    cd ${S}
    cargo build --release
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/target/release/AxImgGen ${D}${bindir}
}
