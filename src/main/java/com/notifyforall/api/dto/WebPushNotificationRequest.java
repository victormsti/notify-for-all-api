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
public class WebPushNotificationRequest implements Serializable {

    private String message;
}
