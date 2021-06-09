package com.notifyforall.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class EnableOrDisableNotificationRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean enable;
}
