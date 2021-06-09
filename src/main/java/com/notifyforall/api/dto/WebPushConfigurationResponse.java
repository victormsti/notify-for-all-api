package com.notifyforall.api.dto;

import com.notifyforall.api.model.App;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;


@Data
@NoArgsConstructor
public class WebPushConfigurationResponse implements Serializable {

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

    private AppResponse app;
}
