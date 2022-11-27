package com.awakeseller.awakeseller.controller.user;

import static org.springframework.http.HttpStatus.CREATED;

import java.security.Principal;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.awakeseller.awakeseller.service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(CREATED)
    public UserDTO createUser(@RequestBody final CreateUserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @GetMapping
    public UserDTO getUser(final Principal principal) {
        return userService.findByUserName(principal.getName());
    }
}
