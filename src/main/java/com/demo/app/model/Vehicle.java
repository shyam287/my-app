package com.demo.app.model;

import com.demo.app.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * we could create Vehicle as abstract class and have specific vehicle subclasses CAR, BIKe etc.
 * For this use case, I am going with vehicle Type which would tell type of vehicle like CAR and Bike.
 */
@Getter
@AllArgsConstructor
@ToString
public class Vehicle {

    private final int vehicleId;

    private final VehicleType vehicleType;
}
