package com.demo.app.service;

import com.demo.app.model.ParkingTicket;
import com.demo.app.model.Spot;
import com.demo.app.model.Vehicle;
import com.demo.app.service.ParkingLot;
import com.demo.app.util.TestUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.demo.app.enums.VehicleType.CAR;
import static com.demo.app.util.TestUtil.getVehicle;
import static com.demo.app.util.TestUtil.initializeParkingLot;
import static org.junit.Assert.*;

public class ParkingLotTest {

    ParkingLot parkingLot;

    @Before
    public void before() {
        parkingLot = initializeParkingLot(10);
    }

    @Test
    public void shouldReturnParkingTicket_When_VehicleIsParked() {

        Optional<ParkingTicket> parkingTicket = parkingLot.parkVehicle(getVehicle());

        assertTrue(parkingTicket.isPresent());
        assertEquals(getVehicle().getVehicleId(), parkingTicket.get().getVehicle().getVehicleId());
    }

    @Test
    public void shouldNotReturnParkingTicket_When_SpaceIsFull() {

        TestUtil.bookAllSpots(parkingLot);
        Optional<ParkingTicket> parkingTicket = parkingLot.parkVehicle(getVehicle());

        assertFalse(parkingTicket.isPresent());
    }

    @Test
    public void should_ComputeParkingChargeFourEuro_When_VehicleIsParkedForTwoHour() {

        Spot spot = new Spot(1, CAR, 2);
        LocalDateTime entryTime = LocalDateTime.of(2023, 8, 8, 14,0);
        LocalDateTime exitTime = LocalDateTime.of(2023, 8, 8, 16,0);

        ParkingTicket parkingTicket = new ParkingTicket(new Vehicle(1, CAR), spot, entryTime);

        parkingTicket.setExitTime(exitTime);

        Assert.assertEquals(4L, parkingLot.computeParkingCharge(parkingTicket));
    }
}