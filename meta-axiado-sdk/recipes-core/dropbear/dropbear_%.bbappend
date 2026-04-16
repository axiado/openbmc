FILESEXTRAPATHS:prepend := "${@bb.utils.contains('BUILD_VAR', 'dbg', '${THISDIR}/${PN}:', '', d)}"
