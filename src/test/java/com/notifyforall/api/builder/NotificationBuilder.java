package com.notifyforall.api.builder;

import com.notifyforall.api.dto.EmailNotificationRequest;
import com.notifyforall.api.dto.EmailNotificationResponse;
import com.notifyforall.api.dto.UserAppRequest;
import com.notifyforall.api.dto.UserAppResponse;
import com.notifyforall.api.enums.ChannelType;
import com.notifyforall.api.enums.NotificationOrigin;

import java.time.LocalDate;

public class NotificationBuilder {

    private static final String TEST_STRING = "Test";

    public static EmailNotificationRequest getEmailNotificationRequest(){
        return EmailNotificationRequest.builder()
                .passwordEmail(TEST_STRING)
                .recipientEmail(TEST_STRING)
                .recipientNameEmail(TEST_STRING)
                .senderEmail(TEST_STRING)
                .senderNameEmail(TEST_STRING)
                .subjectEmail(TEST_STRING)
                .textEmail(TEST_STRING)
                .build();
    }

    public static EmailNotificationResponse getEmailNotificationResponse(){
        return EmailNotificationResponse.builder()
                .notificationOrigin(NotificationOrigin.API)
                .recipientEmail(TEST_STRING)
                .recipientNameEmail(TEST_STRING)
                .senderEmail(TEST_STRING)
                .senderNameEmail(TEST_STRING)
                .subjectEmail(TEST_STRING)
                .textEmail(TEST_STRING)
                .channelType(ChannelType.EMAIL)
                .readConfirmation(true)
                .receivedDate(LocalDate.now())
                .sendDate(LocalDate.now())
                .id(1L)
                .build();
    }
}
