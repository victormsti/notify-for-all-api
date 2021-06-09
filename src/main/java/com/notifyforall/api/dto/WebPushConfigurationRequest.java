package com.notifyforall.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class WebPushConfigurationRequest implements Serializable {

    private Long id;
    private String siteMessagingNameWebPush;
    private String siteAddressMessagingWebPush;
    private String siteIconWebPush;
    private String permissionTextMessageWebPush;
    private String permitTextButtonWebPush;
    private String denyTextButtonWebPush;
    private String notificationTitleWebPush;
    private String welcomeTextMessageWebPush;
    private Boolean enableDestinyLinkWebPush;
    private String destinyLinkAddressWebPush;

    private AppRequest app;
}
