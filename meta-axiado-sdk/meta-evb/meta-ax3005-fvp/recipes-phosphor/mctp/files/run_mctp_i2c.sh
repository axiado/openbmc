mctp link set mctpi2c6 up
mctp addr add 8 dev mctpi2c6
mctp neigh add 8 dev mctpi2c6 lladdr 0x10
busctl call au.com.codeconstruct.MCTP1 /au/com/codeconstruct/mctp1/interfaces/mctpi2c6 au.com.codeconstruct.MCTP.BusOwner1 SetupEndpoint ay 1 0x40
