package com.notifyforall.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class AppResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String identifierName;
    private Boolean enableNotificationWebPush;
    private Boolean enableNotificationEmail;
    private Boolean enableNotificationSms;
    private Long idUserApp;
}
