package com.epam.esm.security.service.impl;

import com.epam.esm.security.model.AuthenticationRequest;
import com.epam.esm.security.model.AuthenticationResponse;
import com.epam.esm.security.service.SecurityService;
import com.epam.esm.security.service.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SecurityServiceImpl implements SecurityService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()));

        User user = (User) authenticate.getPrincipal();
        String token = jwtTokenProvider.createToken(user);
        return new AuthenticationResponse(token);
    }
}
