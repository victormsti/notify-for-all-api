package com.notifyforall.api.model;

import com.notifyforall.api.enums.ChannelType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@Table(name = "web_push_user_app_configuration")
public class WebPushUserAppConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_app", referencedColumnName="id",nullable=false)
    private App app;
}
