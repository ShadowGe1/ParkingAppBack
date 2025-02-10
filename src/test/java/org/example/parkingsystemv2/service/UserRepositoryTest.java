package org.example.parkingsystemv2.service;

import org.example.parkingsystemv2.authorization.passwordController.EncryptionPassword;
import org.example.parkingsystemv2.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class UserRepositoryTest {

    private UserRepository userRepository;
    private User user;
    private Logger logger;

    @BeforeEach
    void setUp() {
        logger = LoggerFactory.getLogger(UserRepositoryTest.class);
        userRepository = new UserRepository();
        EncryptionPassword encryptionPassword = new EncryptionPassword();

        user = User.builder()
                .username("username12345")
                .name("Andrei")
                .surname("Clima")
                .email("andreiclima545@gmail.com")
                .password(encryptionPassword.cryptPassword("Test123"))
                .phone("+37369460928")
                .build();
    }

//    @Test
//    void saveUser() {
//        logger.info("userSave");
//        userRepository.saveOrUpdateUser(user);
//        assertNotNull(user.getId());
//    }

//    @Test
//    void getUserById() {
//        Optional<User> result = userRepository.getUserById(user.getId());
//        assertTrue(result.isPresent());
//    }

    @Test
    void getUserByEmail() {
        Optional<User> result = userRepository.getUserByEmail(user.getEmail());
        assertTrue(result.isPresent());
    }

    @Test
    void getUserByUsername() {
        Optional<User> result = userRepository.getUserByUsername(user.getUsername());
        assertTrue(result.isPresent());
    }

//    @Test
//    void deleteUserById() {
//        userRepository.deleteUserById(user.getId());
//        Optional<User> user = userRepository.getUserById(this.user.getId());
//        assertTrue(user.isEmpty());
//    }

    @Test
    void existsUserByEmail() {
        assertTrue(userRepository.existsUserByEmail(user.getEmail()));
    }

    @Test
    void existsUserByUsername() {
        assertTrue(userRepository.existsUserByUsername(user.getUsername()));
    }

    @Test
    void getPasswordByEmail() {
        System.out.println(userRepository.getPasswordByEmail(user.getEmail()));
        assertNotEquals(userRepository.getPasswordByEmail(user.getEmail()), "");
    }
}
