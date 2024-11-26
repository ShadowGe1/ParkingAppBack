package org.example.parkingsystemv2.authorization.sign_in;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignInTest {
    private Sign_IN sign_in;

    @BeforeEach
    void setUp() {
        String email = "andreiclima54@gmail.com";
        String password = "Test123";
        sign_in = new Sign_IN(email, password);
    }

    @Test
    void checkEmail() {
        assertTrue(sign_in.checkEmail());
    }

    @Test
    void checkPasswordTrue() {
        assertTrue(sign_in.checkPassword());
    }
}