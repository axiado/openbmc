# Copyright (c) 2021-26 Axiado Corporation (or its affiliates). All rights reserved.

SUMMARY = "Caliptra software image bundle"
SECTION = "axiado/firmware"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

inherit cargo deploy

# Disable the class's built-in vendoring; crates are fetched directly from the
# network during do_compile.
CARGO_DISABLE_BITBAKE_VENDORING = "1"

# Copy BitBake's generated config.toml (which contains the x86_64 linker
# wrapper for crate build scripts) to ${WORKDIR}/.cargo/ so cargo finds it
# via the upward config search when CARGO_HOME is unset in do_compile.
do_configure:append() {
    mkdir -p "${WORKDIR}/.cargo"
    cp "${CARGO_HOME}/config.toml" "${WORKDIR}/.cargo/config.toml"
}

do_compile[network] = "1"
do_compile[cleandirs] = "${B}"

do_compile() {
    # caliptra-builder embeds env!("CARGO") at compile time, so the cargo used
    # here must be the system rustup cargo (which has riscv32imc-unknown-none-elf).
    # BitBake's sysroot cargo lacks that target; unset CARGO_HOME so the system
    # cargo uses ~/.cargo, and prepend it to PATH so it wins over the sysroot cargo.
    # Use $HOME/$PATH (no braces) so BitBake leaves them as shell variable refs.
    unset CARGO_HOME
    export PATH="$HOME/.cargo/bin:$PATH"
    # Rust 1.85: the codebase uses --check-cfg which was stabilised in 1.80.
    export RUSTUP_TOOLCHAIN="1.85"
    # caliptra-builder (lib.rs) reads CARGO_TARGET_DIR to locate riscv32imc ELFs;
    # must match the target-dir in ${WORKDIR}/.cargo/config.toml set by the cargo class.
    export CARGO_TARGET_DIR="${B}/target"
    # Build caliptra-builder (runs on host, cross-compiles RISC-V firmware
    # internally) then invoke it to assemble image-bundle.bin.
    cargo build --release \
        --manifest-path ${S}/Cargo.toml \
        --package caliptra-builder \
        --bin image

    install -d ${B}
    # Run image from ${S} so git rev-parse HEAD finds the checked-out repo.
    (cd "${S}" && ${B}/target/release/image --fw ${B}/image-bundle.bin)
}

do_install() {
    install -d ${D}${nonarch_base_libdir}/firmware/caliptra
    install -m 0644 ${B}/image-bundle.bin ${D}${nonarch_base_libdir}/firmware/caliptra/image-bundle.bin
}

do_deploy() {
    install -m 0644 ${B}/image-bundle.bin ${DEPLOYDIR}
}

FILES:${PN} += "${nonarch_base_libdir}/firmware/caliptra/image-bundle.bin"

addtask do_deploy before do_build after do_install
