#!/bin/sh

# Remove existing endpoint
busctl call au.com.codeconstruct.MCTP1 /au/com/codeconstruct/mctp1/networks/1/endpoints/18 au.com.codeconstruct.MCTP.Endpoint1 Remove

# Do MCTP discover
busctl call au.com.codeconstruct.MCTP1 /au/com/codeconstruct/mctp1/interfaces/mctpserial0 au.com.codeconstruct.MCTP.BusOwner1 SetupEndpoint ay 0
