package com.notifyforall.api.controller;

import com.notifyforall.api.builder.NotificationBuilder;
import com.notifyforall.api.dto.*;
import com.notifyforall.api.service.NotificationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
public class NotificationControllerTest {

    EmailNotificationRequest emailNotificationRequest = null;
    SmsNotificationRequest smsNotificationRequest = null;
    ResponseEntity<EmailNotificationResponse> emailNotificationResponse = null;
    ResponseEntity<SmsNotificationResponse> smsNotificationResponse = null;
    ResponseEntity<List<EmailNotificationResponse>> emailNotificationResponseList = null;
    ResponseEntity<List<SmsNotificationResponse>> smsNotificationResponseList = null;

    @Mock
    private NotificationService notificationService;

    @Mock
    private NotificationController notificationController;

    @Before
    public void init(){
        emailNotificationRequest = NotificationBuilder.getEmailNotificationRequest();
        emailNotificationResponse= ResponseEntity.ok().body(NotificationBuilder.getEmailNotificationResponse());
        smsNotificationRequest = SmsNotificationRequest.builder().build();
        smsNotificationResponse = ResponseEntity.ok().body(SmsNotificationResponse.builder().build());
        emailNotificationResponseList = ResponseEntity.ok().body(Collections.singletonList(NotificationBuilder.getEmailNotificationResponse()));
        smsNotificationResponseList = ResponseEntity.ok().body(Collections.singletonList(SmsNotificationResponse.builder().build()));
    }

    @Test
    public void whenSendEmailNotificationThenSuceed(){
        doReturn(emailNotificationResponse).when(notificationController).sendEmailNotification(1L,1L,"API",emailNotificationRequest);

        ResponseEntity<EmailNotificationResponse> response = notificationController.sendEmailNotification(1L,1L,"API",emailNotificationRequest);

        assertEquals(emailNotificationResponse.getStatusCode(),response.getStatusCode());
        assertEquals(emailNotificationResponse.getBody(),response.getBody());
    }

    @Test
    public void whenSendSmsNotificationhanSuceed(){
        doReturn(smsNotificationResponse).when(notificationController).sendSmsNotification(1L,1L,"API", smsNotificationRequest);

        ResponseEntity<SmsNotificationResponse> response = notificationController.sendSmsNotification(1L,1L,"API",smsNotificationRequest);

        assertEquals(smsNotificationResponse.getStatusCode(),response.getStatusCode());
        assertEquals(smsNotificationResponse.getBody(),response.getBody());
    }

    @Test
    public void whenFindAllEmailNotificationsByIdAppThenSuceed(){
        doReturn(emailNotificationResponseList).when(notificationController).findAllEmailNotificationsByIdApp(1L);

        ResponseEntity<List<EmailNotificationResponse>> response = notificationController.findAllEmailNotificationsByIdApp(1L);

        assertEquals(emailNotificationResponseList.getStatusCode(),response.getStatusCode());
        assertEquals(emailNotificationResponseList.getBody(),response.getBody());
    }

    @Test
    public void whenFindAllSmsNotificationsByIdAppThenSuceed(){
        doReturn(smsNotificationResponseList).when(notificationController).findAllSmsNotificationsByIdApp(1L);

        ResponseEntity<List<SmsNotificationResponse>> response = notificationController.findAllSmsNotificationsByIdApp(1L);

        assertEquals(smsNotificationResponseList.getStatusCode(),response.getStatusCode());
        assertEquals(smsNotificationResponseList.getBody(),response.getBody());
    }

}
