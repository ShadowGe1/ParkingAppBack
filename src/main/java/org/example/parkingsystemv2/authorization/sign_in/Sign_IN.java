package org.example.parkingsystemv2.authorization.sign_in;

import org.example.parkingsystemv2.authorization.passwordController.EncryptionPassword;
import org.example.parkingsystemv2.service.UserRepository;

public class Sign_IN {
    private final String email;
    private final String password;
    private final UserRepository userRepository;
    private final EncryptionPassword encryptionPassword;

    public Sign_IN(String email, String password) {
        this.email = email;
        this.password = password;
        this.userRepository = new UserRepository();
        this.encryptionPassword = new EncryptionPassword();
    }

    public boolean checkEmail() {
        return userRepository.existsUserByEmail(email);
    }

    public boolean checkPassword() {
        return encryptionPassword.checkPassword(password, userRepository.getPasswordByEmail(email));
    }
}
