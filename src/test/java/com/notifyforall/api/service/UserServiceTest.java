package com.notifyforall.api.service;

import com.notifyforall.api.builder.UserAppBuilder;
import com.notifyforall.api.dto.UserAppRequest;
import com.notifyforall.api.dto.UserAppResponse;
import com.notifyforall.api.mapper.UserAppMapper;
import com.notifyforall.api.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    UserAppRequest userAppRequest = null;
    UserAppResponse userAppResponse = null;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserAppMapper mapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserService userService;

    @Before
    public void init(){
    userAppRequest = UserAppBuilder.getUserAppRequest();
    userAppResponse = UserAppBuilder.getUserAppResponse();
    }

    @Test
    public void whenRegisterUserThenSuceed(){
        doReturn(userAppResponse).when(userService).signUpUser(userAppRequest);

        UserAppResponse response = userService.signUpUser(userAppRequest);

        assertEquals(userAppResponse,response);
    }

    @Test
    public void whenFindLoggedUserThenSuceed(){
        doReturn(userAppResponse).when(userService).findLoggedUser();

        UserAppResponse response = userService.findLoggedUser();

        assertEquals(userAppResponse,response);
    }
}
