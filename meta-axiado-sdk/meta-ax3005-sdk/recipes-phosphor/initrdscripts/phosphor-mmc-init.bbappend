FILESEXTRAPATHS:append := "${THISDIR}/files:"

# Add libubootenv-bin into the initramfs only when the u-boot-fw-utils provider is libubootenv
RDEPENDS:${PN}:append = " ${@bb.utils.contains('PREFERRED_PROVIDER_u-boot-fw-utils', 'libubootenv', 'libubootenv-bin', '', d)}"

# Remove GPIO option from factory reset feature to save space
RDEPENDS:${PN}:remove = "libgpiod-tools"
# Remove secondary GPT feature to save space
RDEPENDS:${PN}:remove = "gptfdisk parted"
