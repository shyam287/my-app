package com.demo.app.model;

import com.demo.app.service.ParkingLot;

import java.time.LocalDateTime;

public class ExitGate extends Gate {

    ParkingLot parkingLot;

    public ExitGate(int gateId, ParkingLot parkingLot) {
        super(gateId);
        this.parkingLot = parkingLot;
    }

    public long computeCharge(ParkingTicket parkingTicket) {
        parkingTicket.setExitTime(LocalDateTime.now());
        return parkingLot.computeParkingCharge(parkingTicket);
    }
}
