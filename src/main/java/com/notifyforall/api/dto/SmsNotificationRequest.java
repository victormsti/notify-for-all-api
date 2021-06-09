package com.notifyforall.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SmsNotificationRequest implements Serializable {

    private String senderPhoneNumber;
    private String receiverPhoneNumber;
    private String textSms;
}
