package com.notifyforall.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class SmsConfigurationResponse implements Serializable {

    private Long id;
    private String providerSms;
    private String loginSms;
    private String passwordSms;
    private String textSms;

    private AppRequest app;
}
