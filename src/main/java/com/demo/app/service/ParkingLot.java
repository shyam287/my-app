package com.demo.app.service;

import com.demo.app.enums.VehicleType;
import com.demo.app.model.ParkingTicket;
import com.demo.app.model.Spot;
import com.demo.app.model.Vehicle;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * assumption is that its single floor parking space
 * to implement multiple levels, ParkingLot class can have Lis of ParkingFloor
 * and ParkingFloor will have ParkingSpot along with floor attribute like floorNo..
 */
public class ParkingLot {

    private Map<VehicleType, List<Spot>> spotsByVehicleTypeMap;

    private Lock lock = new ReentrantLock();

    public ParkingLot(Map<VehicleType, List<Spot>> spotsByVehicleTypeMap) {
        this.spotsByVehicleTypeMap = spotsByVehicleTypeMap;
    }

    public Map<VehicleType, List<Spot>> getSpotsByVehicleTypeMap() {
        return spotsByVehicleTypeMap;
    }

    public Optional<ParkingTicket> parkVehicle(Vehicle vehicle) {

        Spot spot;
        lock.lock();

        try {

            Optional<Spot> freeSpot = findEmptySpot(vehicle.getVehicleType());

            if (!freeSpot.isPresent()) {
                return Optional.empty();
            }

            spot = freeSpot.get();
            spot.setAvailable(false);

        } finally {
            lock.unlock();
        }

        return Optional.of(parkVehicleAtSpot(vehicle, spot));
    }

    private ParkingTicket parkVehicleAtSpot(Vehicle vehicle, Spot spot) {

            spot.setVehicle(vehicle);
            return generateTicket(spot);
    }

    private ParkingTicket generateTicket(Spot spot) {
        return new ParkingTicket(spot.getVehicle(), spot, LocalDateTime.now());
    }

    private Optional<Spot> findEmptySpot(VehicleType vehicleType) {

        return spotsByVehicleTypeMap.get(vehicleType).stream().filter(Spot::isAvailable).findFirst();
    }

    public long computeParkingCharge(ParkingTicket ticket) {

        Duration timeDiff = Duration.between(ticket.getEntryTime(), ticket.getExitTime());

//        LocalDateTime time = LocalDateTime.of(2023,8, 9, 0, 0);
//        Duration timeDiff = Duration.between(ticket.getExitTime(), time);

        long parkingHour = timeDiff.toHours();

        long cost = ticket.getSpot().getCostPerHour() * parkingHour;

        ticket.getSpot().setAvailable(true);

        return cost;
    }
}
