package com.epam.esm.security.service;

import com.epam.esm.security.model.AuthenticationResponse;
import com.epam.esm.security.model.AuthenticationRequest;

public interface SecurityService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
