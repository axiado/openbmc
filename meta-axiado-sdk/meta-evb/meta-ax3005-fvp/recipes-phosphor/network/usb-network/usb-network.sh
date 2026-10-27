#!/bin/bash

ENV_MAC_ADDR="72:45:77:AA:F7:88"
HOST_MAC_ADDR="72:45:77:AA:F7:89"

/usr/bin/usb-ctrl ecm usbnet on $ENV_MAC_ADDR $HOST_MAC_ADDR

# NCM Gadget Product ID for the kernel
echo 0x0103 > /sys/kernel/config/usb_gadget/usbnet/idProduct
echo "OpenBMC usbnet Device" > /sys/kernel/config/usb_gadget/usbnet/strings/0x409/product