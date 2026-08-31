FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# Revert it because we did not implement Axiado WDT in QEMU yet
SRC_URI += "file://0001-Revert-board-axiado-scm3005-Support-scratchpad-watch.patch"
