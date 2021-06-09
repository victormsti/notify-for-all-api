package com.notifyforall.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAppRequest implements Serializable {

    private Long id;
    private String username;
    private String email;
    private String companyName;
    private String phone;
    private String address;
    private String password;
    private Boolean automaticLogin;
}
