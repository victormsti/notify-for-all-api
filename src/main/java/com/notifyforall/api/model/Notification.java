package com.notifyforall.api.model;

import com.notifyforall.api.enums.ChannelType;
import com.notifyforall.api.enums.NotificationOrigin;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(name = "notification")
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NotificationOrigin notificationOrigin;

    @Enumerated(EnumType.STRING)
    private ChannelType channelType;

    @Column
    private LocalDate sendDate;

    @Column
    private LocalDate receivedDate;

    @Column
    private Boolean readConfirmation;

    @Column
    private String senderPhoneNumber;

    @Column
    private String receiverPhoneNumber;

    @Column
    private String senderNameEmail;

    @Column
    private String senderEmail;

    @Column
    private String RecipientNameEmail;

    @Column
    private String RecipientEmail;

    @Column
    private String textEmail;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_web_push_user_app_configuration", referencedColumnName = "id")
    private WebPushUserAppConfiguration webPushUserAppConfiguration;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_email_user_app_configuration", referencedColumnName = "id")
    private EmailUserAppConfiguration emailUserAppConfiguration;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sms_user_app_configuration", referencedColumnName = "id")
    private SmsUserAppConfiguration smsUserAppConfiguration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_app", referencedColumnName="id",nullable=false)
    private App app;
}
