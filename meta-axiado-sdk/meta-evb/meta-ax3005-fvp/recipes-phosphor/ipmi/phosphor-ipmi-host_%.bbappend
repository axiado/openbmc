SERIAL_DEVICE = "ttyS2"

PACKAGECONFIG:remove = "transport-null"
PACKAGECONFIG:append = " \
    transport-serial \
    arm-sbmr \
"
