package com.notifyforall.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class SmsConfigurationRequest implements Serializable {

    private Long id;
    private String providerSms;
    private String loginSms;
    private String passwordSms;
}
