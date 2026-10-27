#!/bin/bash

# checking for Host state
if [ $# -lt 1 ]; then
  echo "Insufficient parameter = $#"
  exit 0;
fi

# checking gpio config
if [ -z "$PGOOD_OK" ]; then
  echo "PGOOD_OK env is not set"
  exit 0;
fi

gpio_line=$(gpiofind "$PGOOD_OK")

# Time out checking for Host is 120s
cnt=120
while [ "$cnt" -gt 0 ];
do
  cnt=$((cnt - 1))
  st=$(gpioget $gpio_line)
  if [ "$st" == "$1" ]; then
    busctl set-property xyz.openbmc_project.State.Host0 \
        /xyz/openbmc_project/state/host0 xyz.openbmc_project.State.Host \
        CurrentHostState s xyz.openbmc_project.State.Host.HostState.Running
    exit 0
  fi
  sleep 1
done

exit 1

