package com.demo.app.util;

import com.demo.app.enums.VehicleType;
import com.demo.app.service.ParkingLot;
import com.demo.app.model.Spot;
import com.demo.app.model.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.demo.app.enums.VehicleType.CAR;

public class TestUtil {

    public  static ParkingLot initializeParkingLot(int parkingSpace) {

        List<Spot> spotList = new ArrayList<>(parkingSpace);

        for(int i=0; i < parkingSpace; i++) {

            spotList.add(new Spot(i, CAR, 2));
        }

        Map<VehicleType, List<Spot>> spotsByVehicleType = new HashMap<>();
        spotsByVehicleType.put(CAR, spotList);

        return new ParkingLot(spotsByVehicleType);
    }

    public  static void bookAllSpots(ParkingLot parkingLot) {

        for(Spot spot : parkingLot.getSpotsByVehicleTypeMap().get(CAR)) {
            spot.setAvailable(false);
        }
    }

    public static Vehicle getVehicle() {
        return new Vehicle(1, CAR);
    }
}
