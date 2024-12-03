package org.example.parkingsystemv2.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.parkingsystemv2.entity.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@AllArgsConstructor
@Slf4j
public class VehicleRepository {
    private final SessionFactory sessionFactory;

    public void saveVehicle(Vehicle vehicle) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

        }
    }
}
