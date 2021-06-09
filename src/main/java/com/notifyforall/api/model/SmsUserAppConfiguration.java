package com.notifyforall.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@Table(name = "sms_user_app_configuration")
public class SmsUserAppConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String providerSms;
    private String loginSms;
    private String passwordSms;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_app", referencedColumnName="id",nullable=false)
    private App app;
}
