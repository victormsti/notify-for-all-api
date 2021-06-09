package com.notifyforall.api.controller;

import com.notifyforall.api.builder.UserAppBuilder;
import com.notifyforall.api.dto.UserAppRequest;
import com.notifyforall.api.dto.UserAppResponse;
import com.notifyforall.api.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserControllerTest {

    UserAppRequest userAppRequest = null;
    ResponseEntity<UserAppResponse> userAppResponse = null;

    @Mock
    private UserService userService;

    @Mock
    private UserAppController userAppController;

    @Before
    public void init(){
    userAppRequest = UserAppBuilder.getUserAppRequest();
    userAppResponse = ResponseEntity.ok().body(UserAppBuilder.getUserAppResponse());
    }

    @Test
    public void whenRegisterUserThenSuceed(){
        doReturn(userAppResponse).when(userAppController).signUpUser(userAppRequest);

        ResponseEntity<UserAppResponse> response = userAppController.signUpUser(userAppRequest);

        assertEquals(userAppResponse.getStatusCode(),response.getStatusCode());
        assertEquals(userAppResponse.getBody(),response.getBody());
    }

    @Test
    public void whenFindLoggedUserThenSuceed(){
        doReturn(userAppResponse).when(userAppController).findLoggedUser();

        ResponseEntity<UserAppResponse> response = userAppController.findLoggedUser();

        assertEquals(userAppResponse.getStatusCode(),response.getStatusCode());
    }
}
