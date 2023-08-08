package com.demo.app.model;

import com.demo.app.service.ParkingLot;

import java.util.Optional;

public class EntranceGate extends Gate {

    private ParkingLot parkingLot;

    public EntranceGate(int gateId, ParkingLot parkingLot) {
        super(gateId);
        this.parkingLot = parkingLot;
    }

    public Optional<ParkingTicket> parkVehicle(Vehicle vehicle) {

        return parkingLot.parkVehicle(vehicle);
    }
}
