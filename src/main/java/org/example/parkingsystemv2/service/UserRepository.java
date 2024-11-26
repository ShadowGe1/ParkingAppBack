package org.example.parkingsystemv2.service;

import lombok.extern.slf4j.Slf4j;
import org.example.parkingsystemv2.config.ConfigBD;
import org.example.parkingsystemv2.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Optional;

@Slf4j
public class UserRepository {

    SessionFactory sessionFactory = ConfigBD.getSessionFactory();

    public void saveOrUpdateUser(User user) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            if (user.getId() == null) {
                session.persist(user);
            } else {
                session.merge(user);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error saving user", e);
        }
    }

    public Optional<User> getUserById(int id) {
        Optional<User> userOptional = Optional.empty();
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            User user = session.get(User.class, id);
            userOptional = Optional.ofNullable(user);

            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error getting user", e);
        }
        return userOptional;
    }

    public Optional<User> getUserByEmail(String email) {
        Optional<User> userOptional = Optional.empty();

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            User user = session.createQuery("FROM User WHERE email = :email", User.class)
                                .setParameter("email", email)
                                .uniqueResult();

            userOptional = Optional.ofNullable(user);

            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error getting user", e);
        }

        return userOptional;
    }

    public Optional<User> getUserByUsername(String username) {
        Optional<User> userOptional = Optional.empty();

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            User user = session.createQuery("FROM User WHERE username = :username", User.class)
                    .setParameter("username", username)
                    .uniqueResult();

            userOptional = Optional.ofNullable(user);

            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error getting user", e);
        }

        return userOptional;
    }

    public void deleteUserById(int id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            User user = session.get(User.class, id);
            session.remove(user);

            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error deleting user", e);
        }
    }

    public boolean existsUserByEmail(String email) {
        Long count = 0L;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            count = session.createQuery("SELECT count(u) FROM User u WHERE u.email = :email", Long.class)
                    .setParameter("email", email)
                    .uniqueResult();

            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error checking if email exists", e);
        }

        return count != null && count > 0;
    }

    public boolean existsUserByUsername(String username) {
        Long count = 0L;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            count = session.createQuery("SELECT count(u) FROM User u WHERE u.username = :username", Long.class)
                    .setParameter("username", username)
                    .uniqueResult();

            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error checking if username exists", e);
        }

        return count != null && count > 0;
    }

    public boolean existsUserByPhone(String phone) {
        Long count = 0L;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            count = session.createQuery("SELECT count(u) FROM User u where u.phone = :phone", Long.class)
                    .setParameter("phone", phone)
                    .uniqueResult();

            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error checking if phone exists", e);
        }

        return count != null && count > 0;
    }

    public String getPasswordByEmail(String email) {
        String password = "";
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            password = session.createQuery("FROM User u WHERE u.email = :email SELECT u.password", String.class)
                    .setParameter("email", email)
                    .uniqueResult();

            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error extracting password", e);
        }
        return password;
    }
}
