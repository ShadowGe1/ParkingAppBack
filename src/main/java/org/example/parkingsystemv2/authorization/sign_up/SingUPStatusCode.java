package org.example.parkingsystemv2.authorization.sign_up;

public enum SingUPStatusCode {
    USERNAME_ALREADY_EXISTS("This username is already in use");

    private final String MESSAGE;

    SingUPStatusCode(String message) {
        this.MESSAGE = message;
    }
}
