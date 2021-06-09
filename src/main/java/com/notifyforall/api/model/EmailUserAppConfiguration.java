package com.notifyforall.api.model;

import com.notifyforall.api.enums.ChannelType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@Table(name = "email_user_app_configuration")
public class EmailUserAppConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "smtp_service_name_email")
    private String smtpServiceNameEmail;

    private String portEmail;
    private String loginEmail;
    private String passwordEmail;
    private String templateEmail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_app", referencedColumnName="id",nullable=false)
    private App app;
}
