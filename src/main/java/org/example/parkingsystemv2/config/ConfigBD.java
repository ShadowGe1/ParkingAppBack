package org.example.parkingsystemv2.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j
public class ConfigBD {
    @Getter
    private final static SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            log.error("Initial SessionFactory creation failed.", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
