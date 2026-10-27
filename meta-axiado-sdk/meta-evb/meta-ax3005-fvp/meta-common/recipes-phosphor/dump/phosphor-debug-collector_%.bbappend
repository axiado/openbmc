SRC_URI += "git://git.gitlab.arm.com/server_management/phosphor-debug-collector.git;branch=fvp_devel;protocol=https"
SRC_URI:remove = "git://github.com/openbmc/phosphor-debug-collector;branch=master;protocol=https"
SRCREV = "5e154105546846d586c1c859d7ba5ac2d2f6bb83"

PACKAGECONFIG[arm-dumps-extension] = " \
       -Darm-dumps-extension=enabled, \
       -Darm-dumps-extension=disabled  \
"

