package com.notifyforall.api.builder;

import com.notifyforall.api.dto.UserAppRequest;
import com.notifyforall.api.dto.UserAppResponse;

public class UserAppBuilder {

    private static final String TEST_STRING = "Test";

    public static UserAppRequest getUserAppRequest(){
        return UserAppRequest.builder()
                .address(TEST_STRING)
                .automaticLogin(true)
                .companyName(TEST_STRING)
                .email(TEST_STRING)
                .id(1L)
                .phone(TEST_STRING)
                .password(TEST_STRING)
                .username(TEST_STRING)
                .build();
    }

    public static UserAppResponse getUserAppResponse(){
        return UserAppResponse.builder()
                .address(TEST_STRING)
                .automaticLogin(true)
                .companyName(TEST_STRING)
                .email(TEST_STRING)
                .id(1L)
                .phone(TEST_STRING)
                .password(TEST_STRING)
                .username(TEST_STRING)
                .build();
    }
}
