package com.thoughtworks.capability.gtb.springdatajpaintro;

public class UserNotExistedException extends RuntimeException {

    public UserNotExistedException(String message) {
        super(message);
    }

}
