package com.notifyforall.api.dto;

import com.notifyforall.api.enums.ChannelType;
import com.notifyforall.api.enums.NotificationOrigin;
import com.notifyforall.api.model.App;
import com.notifyforall.api.model.EmailUserAppConfiguration;
import com.notifyforall.api.model.SmsUserAppConfiguration;
import com.notifyforall.api.model.WebPushUserAppConfiguration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EmailNotificationRequest implements Serializable {

    private String subjectEmail;
    private String textEmail;
    private String senderNameEmail;
    private String senderEmail;
    private String recipientNameEmail;
    private String recipientEmail;
    private String passwordEmail;
}
