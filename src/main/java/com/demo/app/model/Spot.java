package com.demo.app.model;

import com.demo.app.enums.VehicleType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Spot {

    private final int spotId;

    private volatile boolean isAvailable;

    private Vehicle vehicle;

    private final VehicleType vehicleType;

    private final long costPerHour;

    public Spot(int spotId, VehicleType vehicleType, long costPerHour) {
        this.spotId = spotId;
        this.vehicleType = vehicleType;
        this.costPerHour = costPerHour;
        isAvailable = true;
    }
}
