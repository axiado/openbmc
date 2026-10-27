# Setup MCTP USB link
mctp link set mctpusb0 up
mctp addr add 8 dev mctpusb0
mctp route add 9 via mctpusb0

# Do MCTP discover
busctl call au.com.codeconstruct.MCTP1 /au/com/codeconstruct/mctp1/interfaces/mctpusb0 au.com.codeconstruct.MCTP.BusOwner1 SetupEndpoint ay 0
