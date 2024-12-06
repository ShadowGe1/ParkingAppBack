package org.example.parkingsystemv2.service;

import org.example.parkingsystemv2.dto.CoordinateDTO;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
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
        JSONObject json =  new JSONObject();
        json.put("locations", locations);
        try (FileWriter file = new FileWriter("data.json")) {
            file.write(json.toString(4));
        } catch (IOException _) {}
        assertNotNull(locations);
    }
}