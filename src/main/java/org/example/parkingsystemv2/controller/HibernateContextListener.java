package org.example.parkingsystemv2.controller;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import lombok.extern.slf4j.Slf4j;
import org.example.parkingsystemv2.config.ConfigBD;

@Slf4j
public class HibernateContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConfigBD.getSessionFactory();
            log.info("SessionFactory initialized");
        } catch (Exception e) {
            log.error("Error initializing Hibernate SessionFactory: {}", e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConfigBD.shutdown();
        log.info("SessionFactory destroyed");
    }
}
