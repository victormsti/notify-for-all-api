package com.notifyforall.api.service;

import com.notifyforall.api.config.context.UserContext;
import com.notifyforall.api.config.exceptions.RestException;
import com.notifyforall.api.dto.UserAppRequest;
import com.notifyforall.api.dto.UserAppResponse;
import com.notifyforall.api.mapper.UserAppMapper;
import com.notifyforall.api.repository.UserRepository;
import com.notifyforall.api.service.utils.SystemMessages;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserAppMapper mapper;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository,
                       UserAppMapper mapper,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserAppResponse findLoggedUser() {

        return mapper.toResponse(UserContext.getInstance().getUser());
    }

    public UserAppResponse signUpUser(UserAppRequest request){
        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new RestException(HttpStatus.CONFLICT,String.format(SystemMessages.USER_ALREADY_EXISTS_BY_USERNAME, request.getUsername()));
        }

        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RestException(HttpStatus.CONFLICT,String.format(SystemMessages.USER_ALREADY_EXISTS_BY_EMAIL, request.getEmail()));
        }

        request.setPassword(passwordEncoder.encode(request.getPassword()));

        return mapper.toResponse(userRepository.save(mapper.fromRequest(request)));
    }
}
