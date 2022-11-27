package com.awakeseller.awakeseller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User already exists")
public class UserAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = -5942062136299033943L;

    public UserAlreadyExistsException(final String username) {
        super("User " + username + " already exists");
    }

}
