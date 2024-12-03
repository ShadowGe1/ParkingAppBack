package org.example.parkingsystemv2.service;

import org.example.parkingsystemv2.dto.CoordinateDTO;
import org.example.parkingsystemv2.entity.ParkingLocations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLocationsRepositoryTest {

    private ParkingLocationsRepository parkingLocationsRepository;
    @BeforeEach
    void setUp() {
        parkingLocationsRepository = new ParkingLocationsRepository();
    }

    @Test
    void getAllParkingLocations() {
        List<CoordinateDTO> locations = parkingLocationsRepository.getAllParkingLocations();
        System.out.println("Here " + locations);
        assertNotNull(locations);
    }
}