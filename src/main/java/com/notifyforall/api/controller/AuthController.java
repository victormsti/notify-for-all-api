package com.notifyforall.api.controller;

import com.notifyforall.api.service.AuthService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/authentications")
@Api(tags = "Authentications")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping
    public ResponseEntity<String> authenticateUser(@RequestParam String username,
                                                   @RequestParam String password){

        return ResponseEntity.ok().body(authService.authenticateUser(username, password));
    }
}
