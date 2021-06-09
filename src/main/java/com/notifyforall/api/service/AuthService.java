package com.notifyforall.api.service;

import com.notifyforall.api.config.exceptions.RestException;
import com.notifyforall.api.config.security.JWTUtil;
import com.notifyforall.api.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;

    public AuthService(UserRepository userRepository,
                       AuthenticationManager authenticationManager,
                       JWTUtil jwtTokenUtil,
                       JwtUserDetailsService userDetailsService) {

        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    public String authenticateUser(String username, String password){

        authenticateAndGenerateToken(username, password);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return "Bearer " + jwtTokenUtil.generateToken(userDetails);
    }

    private void authenticateAndGenerateToken(String username, String password){

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new RestException(HttpStatus.BAD_REQUEST,"User disabled: " + e);
        } catch (BadCredentialsException e) {
            throw new RestException(HttpStatus.FORBIDDEN,"Invalid credentials: " + e);
        }
    }
}
