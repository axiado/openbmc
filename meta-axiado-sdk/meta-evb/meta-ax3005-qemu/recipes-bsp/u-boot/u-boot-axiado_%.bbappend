FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# FIXME: Update srcrev once Ax3005 QEMU model support WDT
SRCREV = "254e50d5acd330cb628d6ce687be185ced4fa074"
# Revert it because we did not implement Axiado WDT in QEMU yet
SRC_URI += "file://0001-Revert-board-axiado-scm3005-Support-scratchpad-watch.patch"
