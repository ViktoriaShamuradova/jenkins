package com.epam.esm.security.model;

import lombok.Data;

@Data
public class AuthenticationResponse {

    private final String token;

    public AuthenticationResponse(String token) {
        this.token = token;
    }

}
