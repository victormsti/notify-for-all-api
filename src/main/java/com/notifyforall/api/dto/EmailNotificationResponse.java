package com.notifyforall.api.dto;

import com.notifyforall.api.enums.ChannelType;
import com.notifyforall.api.enums.NotificationOrigin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EmailNotificationResponse implements Serializable {

    private Long id;
    private NotificationOrigin notificationOrigin;
    private ChannelType channelType;
    private LocalDate sendDate;
    private LocalDate receivedDate;
    private Boolean readConfirmation;
    private String subjectEmail;
    private String textEmail;
    private String senderNameEmail;
    private String senderEmail;
    private String recipientNameEmail;
    private String recipientEmail;
}
