package com.demo.app;

import com.demo.app.enums.VehicleType;
import com.demo.app.model.*;
import com.demo.app.service.ParkingLot;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.demo.app.enums.VehicleType.CAR;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "App started" );

        int parkingSpace = 100;

       ParkingLot parkingLot =  initializeParkingLot(parkingSpace);

        EntranceGate entranceGate = new EntranceGate(1, parkingLot);

        ExitGate exitGate = new ExitGate(2, parkingLot);

        Vehicle vehicle = new Vehicle(1, CAR);


        // below is to simulate multiple entrances at same time
        ExecutorService enteranceExecuter = Executors.newFixedThreadPool(3);

        for(int i=0 ; i < 110; i++) {
            int finalI = i;
            enteranceExecuter.submit(() -> parkVehicles(entranceGate, finalI));

        }


//        park100Vehicles(entranceGate);

        Optional<ParkingTicket> parkingTicket = entranceGate.parkVehicle(vehicle);

        if (!parkingTicket.isPresent()) {
            System.out.println("NO Free spot available");
            return;
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("parking charge" + exitGate.computeCharge(parkingTicket.get()));
    }

    private static void parkVehicles(EntranceGate entranceGate, int id) {

            Vehicle vehicle = new Vehicle(id, CAR);
            Optional<ParkingTicket> parkingTicket = entranceGate.parkVehicle(vehicle);

            if (!parkingTicket.isPresent()) {
                System.out.println("NO Free spot available");
            } else {

                System.out.println("vehicle with id" + vehicle.getVehicleId() + " parked at "
                        + parkingTicket.get().getSpot().getSpotId());
            }
        }

    private static ParkingLot initializeParkingLot(int parkingSpace) {

        List<Spot> spotList = new ArrayList<>(parkingSpace);

        for(int i=0; i < parkingSpace; i++) {

            spotList.add(new Spot(i, CAR, 2));
        }

        Map<VehicleType, List<Spot>> spotsByVehicleType = new HashMap<>();
        spotsByVehicleType.put(CAR, spotList);

        return new ParkingLot(spotsByVehicleType);

    }
}
