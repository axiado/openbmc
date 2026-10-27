FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

PACKAGECONFIG:append = " no-warm-reboot"
PACKAGECONFIG:remove = " only-allow-boot-when-bmc-ready"

SRC_URI:append = " \
    file://obmc-power-start@.service \
    file://obmc-power-stop@.service \
    file://phosphor-wait-power-off@.service \
    file://phosphor-wait-power-on@.service \
    file://host-poweroff@.service \
    file://phosphor-reset-chassis-running@.service \
    file://host-on-host-check@.service \
    file://mctp-binding.service \
    file://obmc-host-already-on@.target \
    file://host_check.sh \
    "

SRC_URI:append = " \
  file://state_config.ini \
  file://mctp-binding.sh \
"

CHASSIS_DEFAULT_TARGETS:remove = " \
    obmc-chassis-poweron@{}.target.wants/phosphor-fan-presence-tach@{}.service \
"

CHASSIS_DEFAULT_TARGETS:append = " \
    obmc-chassis-poweron@{}.target.requires/phosphor-wait-power-on@{}.service \
    obmc-chassis-poweron@{}.target.requires/host-on-host-check@{}.service \
"

HOST_DEFAULT_TARGETS:remove = " \
    obmc-host-warm-reboot@{}.target.requires/xyz.openbmc_project.Ipmi.Internal.SoftPowerOff.service \
    obmc-host-warm-reboot@{}.target.wants/pldmSoftPowerOff.service \
"

HOST_DEFAULT_TARGETS:append = " \
    obmc-host-shutdown@{}.target.wants/host-poweroff@{}.service \
    obmc-host-startmin@{}.target.requires/host-on-host-check@{}.service \
    obmc-host-already-on@{}.target.wants/mctp-binding.service \
"

RDEPENDS:${PN}:append = " bash"


do_install:append() {
    install -d ${D}${sbindir}
    install -m 0755 ${UNPACKDIR}/host_check.sh ${D}${sbindir}/host_check.sh
    install -m 0755 ${UNPACKDIR}/mctp-binding.sh ${D}${sbindir}/mctp-binding.sh

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${UNPACKDIR}/*.service ${D}${systemd_system_unitdir}/
    install -m 0644 ${UNPACKDIR}/*.target ${D}${systemd_system_unitdir}/
    ln -s obmc-host-already-on@.target ${D}${systemd_system_unitdir}/obmc-host-already-on@0.target

    install -d ${D}${sysconfdir}/default
    install -m 0644 ${UNPACKDIR}/state_config.ini \
        ${D}${sysconfdir}/default/state_config.ini
}

FILES:${PN}-chassis += " ${systemd_system_unitdir}/*.service"
FILES:${PN}-chassis += " ${systemd_system_unitdir}/*.target"
