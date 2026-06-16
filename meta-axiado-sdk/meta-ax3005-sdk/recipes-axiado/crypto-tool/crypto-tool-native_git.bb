SUMMARY = "Axiado crypto tool"
DESCRIPTION = "Axiado signing tool for Caliptra images"

LICENSE = "CLOSED"

SRCREV = "42b66432febd0dd642793f742ba70d47350c9072"
SRC_URI = "git://git@github.com/axiado/crypto;protocol=ssh;branch=develop"

inherit cargo native

# Using cargo to download packages
CARGO_DISABLE_BITBAKE_VENDORING = "1"

# Enable network for the compile task allowing cargo to download dependencies
do_compile[network] = "1"

do_compile() {
    cd ${S}
    cargo build --release
}

do_install () {
    install -d ${D}${bindir}
    install -m 0755 ${B}/target/release/crypt-tool ${D}${bindir}
}
