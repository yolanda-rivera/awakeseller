package com.awakeseller.awakeseller.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.awakeseller.awakeseller.controller.user.CreateUserRequest;
import com.awakeseller.awakeseller.controller.user.UserDTO;

public interface UserService extends UserDetailsService {

    UserDTO createUser(CreateUserRequest userRequest);

    UserDTO findByUserName(String username);
}
