package org.example.parkingsystemv2.authorization.sign_up;

import org.example.parkingsystemv2.authorization.passwordController.EncryptionPassword;
import org.example.parkingsystemv2.entity.User;
import org.example.parkingsystemv2.service.UserRepository;

public class Sign_UP {
    private final UserRepository userRepository;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;
    private final String password;
    private final String confirmPassword;

    public Sign_UP(String username, String firstName, String lastName, String email, String phone, String password, String confirmPassword) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.confirmPassword = confirmPassword;
        userRepository = new UserRepository();
    }

    private boolean verifyPassword() {
        return password.equals(confirmPassword);
    }

    private String verifyUsername() {
        if (userRepository.existsUserByUsername(username)) {
            return "This username is already in use";
        }
        return "";
    }

    private String verifyEmail() {
        ValidationEmail validEmail = new ValidationEmail();
        if (!validEmail.isEmailValid(email)) {
            return "This email is not valid";
        } else if (userRepository.existsUserByEmail(email)) {
            return "This email is already in use";
        }
        return "";
    }

    private String cryptPassword() {
        EncryptionPassword encryptionPassword = new EncryptionPassword();
        return encryptionPassword.cryptPassword(password);
    }

    private String verifyPhone() {
        ParseNumberForVerify parseNumberForVerify = new ParseNumberForVerify();
        if (!parseNumberForVerify.verify(phone)) {
            return "Invalid phone number";
        } else if (userRepository.existsUserByPhone(phone)) {
            return "This phone number is already in use";
        }
        return "";
    }

    public String signUp() {
        if (!verifyUsername().isEmpty()) {
            return verifyUsername();
        } else if (!verifyEmail().isEmpty()) {
            return verifyEmail();
        } else if (verifyPhone().isEmpty()) {
            return verifyPhone();
        } else if (!verifyPassword()) {
            return "The passwords do not match";
        }

        User user = User.builder()
                .username(username)
                .name(firstName)
                .surname(lastName)
                .email(email)
                .phone(phone)
                .password(cryptPassword())
                .build();

        userRepository.saveOrUpdateUser(user);
        return "";
    }
}
