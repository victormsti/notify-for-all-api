package com.notifyforall.api.service;

import com.notifyforall.api.builder.NotificationBuilder;
import com.notifyforall.api.dto.EmailNotificationRequest;
import com.notifyforall.api.dto.EmailNotificationResponse;
import com.notifyforall.api.dto.SmsNotificationRequest;
import com.notifyforall.api.dto.SmsNotificationResponse;
import com.notifyforall.api.mapper.NotificationMapper;
import com.notifyforall.api.repository.AppRepository;
import com.notifyforall.api.repository.EmailConfigurationRepository;
import com.notifyforall.api.repository.NotificationRepository;
import com.notifyforall.api.repository.SmsConfigurationRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
public class NotificationServiceTest {

    EmailNotificationRequest emailNotificationRequest = null;
    SmsNotificationRequest smsNotificationRequest = null;
    ResponseEntity<EmailNotificationResponse> emailNotificationResponse = null;
    SmsNotificationResponse smsNotificationResponse = null;
    List<EmailNotificationResponse> emailNotificationResponseList = null;
    List<SmsNotificationResponse> smsNotificationResponseList = null;

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private AppRepository appRepository;

    @Mock
    private EmailConfigurationRepository emailConfigurationRepository;

    @Mock
    private SmsConfigurationRepository smsConfigurationRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private NotificationMapper mapper;

    @Mock
    private NotificationService notificationService;

    @Before
    public void init(){
        emailNotificationRequest = NotificationBuilder.getEmailNotificationRequest();
        emailNotificationResponse= ResponseEntity.ok().body(NotificationBuilder.getEmailNotificationResponse());
        smsNotificationRequest = SmsNotificationRequest.builder().build();
        smsNotificationResponse =SmsNotificationResponse.builder().build();
        emailNotificationResponseList = Collections.singletonList(NotificationBuilder.getEmailNotificationResponse());
        smsNotificationResponseList = Collections.singletonList(SmsNotificationResponse.builder().build());
    }

    @Test
    public void whenSendEmailNotificationThenSuceed(){
        EmailNotificationResponse emailNotificationResponse = EmailNotificationResponse.builder().build();
        doReturn(emailNotificationResponse).when(notificationService).sendEmailNotification(1L,1L,"API",emailNotificationRequest);

        EmailNotificationResponse response = notificationService.sendEmailNotification(1L,1L,"API",emailNotificationRequest);

        assertEquals(emailNotificationResponse,response);
    }

    @Test
    public void whenSendSmsNotificationThenSuceed(){
        doReturn(smsNotificationResponse).when(notificationService).sendSmsNotification(1L,1L,"API", smsNotificationRequest);

        SmsNotificationResponse response = notificationService.sendSmsNotification(1L,1L,"API",smsNotificationRequest);

        assertEquals(smsNotificationResponse,response);
    }

    @Test
    public void whenFindAllEmailNotificationsByIdAppThenSuceed(){
        doReturn(emailNotificationResponseList).when(notificationService).findAllEmailNotificationsByIdApp(1L);

        List<EmailNotificationResponse> response = notificationService.findAllEmailNotificationsByIdApp(1L);

        assertEquals(emailNotificationResponseList,response);
    }

    @Test
    public void whenFindAllSmsNotificationsByIdAppThenSuceed(){
        doReturn(smsNotificationResponseList).when(notificationService).findAllSmsNotificationsByIdApp(1L);

        List<SmsNotificationResponse> response = notificationService.findAllSmsNotificationsByIdApp(1L);

        assertEquals(smsNotificationResponseList,response);
    }

}
