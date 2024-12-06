package org.example.parkingsystemv2.service;

import lombok.extern.slf4j.Slf4j;
import org.example.parkingsystemv2.config.ConfigBD;
import org.example.parkingsystemv2.dto.CoordinateDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

@Slf4j
public class ParkingLocationsRepository {

    private final SessionFactory sessionFactory = ConfigBD.getSessionFactory();

    public List<CoordinateDTO> getAllParkingLocations() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            List<CoordinateDTO> locations = session.createQuery("SELECT new org.example.parkingsystemv2.dto.CoordinateDTO(latitude, longitude) FROM ParkingLocations", CoordinateDTO.class)
                                                    .list();

            session.getTransaction().commit();
            return locations;
        } catch (Exception e) {
            log.error("Error getting all parking locations", e);
            return null;
        }
    }
}
