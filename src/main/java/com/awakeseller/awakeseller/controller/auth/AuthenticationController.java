package com.awakeseller.awakeseller.controller.auth;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.awakeseller.awakeseller.config.jwt.JwtTokenUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping(consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
    public AuthTokenDTO createToken(@RequestParam final Map<String, String> body) throws AuthenticationException {

        final var userPassAuthToken = new UsernamePasswordAuthenticationToken(
                body.get("username"),
                body.get("password"));

        final var authentication = authenticationManager.authenticate(userPassAuthToken);

        return new AuthTokenDTO(jwtTokenUtil.generateToken(authentication));
    }

}