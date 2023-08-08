package com.demo.app.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class ParkingTicket {

    private Vehicle vehicle;
    private Spot spot;

    private LocalDateTime entryTime;

    private LocalDateTime exitTime;

    private int cost;

    public ParkingTicket(Vehicle vehicle, Spot spot, LocalDateTime entryTime) {
        this.vehicle = vehicle;
        this.spot = spot;
        this.entryTime = entryTime;
    }
}
