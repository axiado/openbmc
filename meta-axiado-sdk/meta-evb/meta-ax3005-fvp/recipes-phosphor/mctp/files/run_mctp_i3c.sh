mctp link set mctpi3c1 up
mctp addr add 8 dev mctpi3c1
mctp neigh add 8 dev mctpi3c1 lladdr 07:ec:80:01:00:00
busctl call au.com.codeconstruct.MCTP1 /au/com/codeconstruct/mctp1/interfaces/mctpi3c1 au.com.codeconstruct.MCTP.BusOwner1 SetupEndpoint ay 6 0x07 0xec 0x80 0x01 0x00 0x00
