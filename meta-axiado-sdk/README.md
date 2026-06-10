# Introduction

## This repository contains the layers for Axiado TCU.

* meta-ax3000-sdk

  This layer contains the following recipes for Axiado AX3000 SoC :
  - `U-boot`    : 2019.04
  - `kernel`    : 6.6.106
  - `Logmgr`    : Axiado log manager
  - `Provision` : Axiado signing tool
  - `Sysproxy`  : System manager proxy
  - `TDFU`      : Tool for TCU device firmware update

* meta-ax3005-sdk

  This layer contains the following recipes for Axiado AX3005 SoC :
  - `U-boot`             : 2026.04
  - `kernel`             : 6.6.106
  - `Logmgr`             : Axiado log manager
  - `Caliptra-sw`        : Caliptra firmware and software
  - `Kw-hal`             : A hardware abstraction layer (HAL) SDK with FreeRTOS integration
  - `Optee-os`           : OP-TEE Trusted OS
  - `Trusted-firmware-a` : Trusted Firmware for A profile Arm CPUs
  - `Irot`               : Axiado iRoT firmware
  - `Caliptra-img-gen`   : Axiado tool to generate image for SPI flash programming

* meta-evb

  This layer contains the basic builds for Axiado TCU.

## Others

* recipes-axiado

  This dirctory contains Axiado-specific tools such as TDFU sysproxy etc.

* recipes-bsp

  This dirctory contains Axiado u-boot and recipes.

* recipes-kernel

  This dirctory contains Axiado Linux kernel and modules.

## Prerequisite for signing images for Ax3000 SoC with Axiado SDK

```sh
git clone https://github.com/axiado/kirkwood-image-tool.git
pip install -r kirkwoods-image-tool/requirements.txt
```

## Prerequisite for building Caliptra-sw for Ax3005 SoC with Axiado SDK

```sh
curl --proto --tlsv1.2 -sSf https://sh.rustup.rs | sh -s -- -y
source ~/.cargo/env
rustup toolchain install 1.85
rustup target add riscv32imc-unknown-none-elf --toolchain 1.85
```

## Setting up

1) Download the source

```sh
git clone https://github.com/axiado/openbmc.git
```

2) Target hardware

```
. setup <MACHINE> [BUILD_DIR]
Available machines:
evk-axiado-github
evk-ax3005-github
```

- Ax3000 SoC

```sh
. setup evk-axiado-github [BUILD_DIR]
```

- Ax3005 SoC

```sh
. setup evk-ax3005-github [BUILD_DIR]
```

3) Build

```sh
bitbake obmc-phosphor-image
```

## Artifacts

The output images locates at ${BUILD_DIR}/tmp/deploy/images/${MACHINE}/

###  Boot with Axiado signature images for Ax3000 SoC
- `u-boot-${MACHINE}.bin` : Original u-boot image
- `fitImage-${MACHINE}.bin` : Original kernel image in FIT format including DTBs and initramfs
- `obmc-phosphor-image-${MACHINE}.wic.xz` : Compressed mmc image with pre-defined layout for user partition
- `obmc-phosphor-image-${MACHINE}.ext4.mmc.tar` : Compressed BMC images with Axiado signature for firmware update

###  Boot with Caliptra manifest image for Ax3005 SoC
- `u-boot-${MACHINE}.bin` : Original u-boot image
- `fitImage-${MACHINE}.bin` : Original kernel image in FIT format including DTBs and initramfs
- `obmc-phosphor-image-${MACHINE}.squashfs-xz` : Compressed ROFS image
- `obmc-phosphor-image-${MACHINE}.wic.xz` : Compressed mmc image with pre-defined layout for user partition
- `sbl.bin` : Axiado secondary bootloader image
- `image-bundle.bin` : Caliptra bundle image
- `sysmgr.bin` : Axiado iRoT image
- `secmc.img.ebin` : Security Management Complex image
- `tee-pager_v2.bin` : OP-TEE image
- `bl31.bin` : Trusted Firmware A image
- `axiado-flash-${MACHINE}.bin` : Axiado bundle image for SPI flash
