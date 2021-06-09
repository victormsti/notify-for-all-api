package com.notifyforall.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EmailConfigurationRequest implements Serializable {

    private Long id;
    private String smtpServiceNameEmail;
    private String portEmail;
    private String loginEmail;
    private String passwordEmail;
    private String templateEmail;
}
