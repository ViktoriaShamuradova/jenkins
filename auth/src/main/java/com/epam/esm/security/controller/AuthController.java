package com.epam.esm.security.controller;

import com.epam.esm.dto.RegistrationUserDto;
import com.epam.esm.dto.UserDto;
import com.epam.esm.security.model.AuthenticationRequest;
import com.epam.esm.security.service.SecurityService;
import com.epam.esm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * a class which performs security operations: authenticate and register user
 */
@RestController
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final SecurityService securityService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    /**
     * a method which create user in database
     *
     * @param registrationUserDto - object witch contains information about user
     * @return a responseEntity with status code and object userDto, which represents a resource "user" from database
     * with links
     */
    @PostMapping("/registration")
    public ResponseEntity<UserDto> register(@RequestBody @Valid RegistrationUserDto registrationUserDto) {
        registrationUserDto.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        UserDto userCreated = userService.create(registrationUserDto);
        return ResponseEntity.ok(userCreated);
    }

    /**
     * a method which authenticate user
     *
     * @param authenticationRequest - object witch contains email and password about user by which check
     * @return a responseEntity with status code, email and token
     * with links
     */
    @PostMapping("/login")
    public ResponseEntity authenticate(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(securityService.authenticate(authenticationRequest));
    }
}
