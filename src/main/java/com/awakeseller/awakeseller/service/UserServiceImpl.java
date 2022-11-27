package com.awakeseller.awakeseller.service;

import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.awakeseller.awakeseller.controller.user.CreateUserRequest;
import com.awakeseller.awakeseller.controller.user.UserDTO;
import com.awakeseller.awakeseller.exception.UserAlreadyExistsException;
import com.awakeseller.awakeseller.model.SecurityUser;
import com.awakeseller.awakeseller.model.User;
import com.awakeseller.awakeseller.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String DEFAULT_ROLE = "USER";

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO createUser(final CreateUserRequest userRequest) {

        if (userExists(userRequest.getUsername())) {
            throw new UserAlreadyExistsException(userRequest.getUsername());
        }

        final var user = new User();
        user.setId(UUID.randomUUID());
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(DEFAULT_ROLE);

        final var savedUser = userRepository.save(user);

        return new UserDTO(savedUser.getId(), savedUser.getUsername());
    }

    private boolean userExists(final String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public UserDTO findByUserName(final String username) {
        return userRepository.findByUsername(username)
                .map(user -> new UserDTO(user.getId(), user.getUsername()))
                .orElseThrow();
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

}