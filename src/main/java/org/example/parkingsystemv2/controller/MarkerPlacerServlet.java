package org.example.parkingsystemv2.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.parkingsystemv2.dto.CoordinateDTO;
import org.example.parkingsystemv2.service.ParkingLocationsRepository;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "marker_placer_servlet", value = "/markerPlacer")
public class MarkerPlacerServlet extends HttpServlet {

    private final ParkingLocationsRepository parkingLocationsRepository = new ParkingLocationsRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<CoordinateDTO> locations = parkingLocationsRepository.getAllParkingLocations();
        JSONObject locationsResponse = new JSONObject();
        locationsResponse.put("locations", locations);

        response.getWriter().write(locationsResponse.toString());
    }
}
