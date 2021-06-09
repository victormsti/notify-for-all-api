package com.notifyforall.api.controller;

import com.notifyforall.api.dto.UserAppRequest;
import com.notifyforall.api.dto.UserAppResponse;
import com.notifyforall.api.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@Api(tags = "Users")
public class UserAppController {

    private final UserService userService;

    @Autowired
    public UserAppController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserAppResponse> findLoggedUser(){
        return ResponseEntity.ok().body(userService.findLoggedUser());
    }

    @PostMapping("sign-ups")
    public ResponseEntity<UserAppResponse> signUpUser(@RequestBody UserAppRequest request){
        return ResponseEntity.ok().body(userService.signUpUser(request));
    }

}
