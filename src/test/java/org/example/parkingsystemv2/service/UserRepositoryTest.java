package org.example.parkingsystemv2.service;

import org.example.parkingsystemv2.authorization.passwordController.EncryptionPassword;
import org.example.parkingsystemv2.config.ConfigBD;
import org.example.parkingsystemv2.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private UserRepository userRepository;
    private User user;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        EncryptionPassword encryptionPassword = new EncryptionPassword();

        user = User.builder()
                .username("username1234")
                .name("Andrei")
                .surname("Clima")
                .email("andreiclima54@gmail.com")
                .password(encryptionPassword.cryptPassword("Test123"))
                .phone("+37369420928")
                .build();
    }

    @Test
    void saveUser() {
        userRepository.saveOrUpdateUser(user);
        assertNotNull(user.getId());
    }

    @Test
    void getUserById() {
        Optional<User> result = userRepository.getUserById(user.getId());
        assertTrue(result.isPresent());
    }

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

    @Test
    void deleteUserById() {
        userRepository.deleteUserById(user.getId());
        Optional<User> user = userRepository.getUserById(this.user.getId());
        assertTrue(user.isEmpty());
    }

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
