package com.notifyforall.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class AppRequest implements Serializable {

    private Long id;
    private String identifierName;
    private Boolean enableNotificationWebPush;
    private Boolean enableNotificationEmail;
    private Boolean enableNotificationSms;
}
