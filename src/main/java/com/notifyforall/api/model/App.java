package com.notifyforall.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@Table(name = "app")
public class App implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identifier_name")
    private String identifierName;

    @Column(name = "enable_notification_web_push")
    private Boolean enableNotificationWebPush;

    @Column(name = "enable_notification_email")
    private Boolean enableNotificationEmail;

    @Column(name = "enable_notification_sms")
    private Boolean enableNotificationSms;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_user_app", referencedColumnName="id",nullable=false)
    private UserApp userApp;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_app", nullable = true)
    private List<Notification> notifications;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_app", nullable = true)
    private List<WebPushUserAppConfiguration> webPushUserAppConfigurations;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_app", nullable = true)
    private List<EmailUserAppConfiguration> emailUserAppConfigurations;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_app", nullable = true)
    private List<SmsUserAppConfiguration> smsUserAppConfigurations;
}
