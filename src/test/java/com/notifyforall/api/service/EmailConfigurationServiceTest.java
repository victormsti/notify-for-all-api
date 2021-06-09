package com.notifyforall.api.service;

import com.notifyforall.api.controller.EmailConfigurationController;
import com.notifyforall.api.dto.EmailConfigurationRequest;
import com.notifyforall.api.dto.EmailConfigurationResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
public class EmailConfigurationServiceTest {

    EmailConfigurationRequest emailConfigurationRequest = null;
    ResponseEntity<EmailConfigurationResponse> emailConfigurationResponse = null;

    @Mock
    private EmailConfigurationService emailConfigurationService;

    @Mock
    private EmailConfigurationController emailConfigurationController;

    @Before
    public void init(){
    emailConfigurationRequest = EmailConfigurationRequest.builder().build();
    emailConfigurationResponse = ResponseEntity.ok().body(EmailConfigurationResponse.builder().build());
    }

    @Test
    public void whenRegisterNewEmailConfigurationThenSuceed(){
        doReturn(emailConfigurationResponse).when(emailConfigurationController).registerNewEmailConfiguration(1L, emailConfigurationRequest);

        ResponseEntity<EmailConfigurationResponse> response = emailConfigurationController.registerNewEmailConfiguration(1L, emailConfigurationRequest);

        assertEquals(emailConfigurationResponse.getStatusCode(),response.getStatusCode());
        assertEquals(emailConfigurationResponse.getBody(),response.getBody());
    }

    @Test
    public void whenGetEmailConfigurationByIdAndIdAppAndUserIdThenSuceed(){
        doReturn(emailConfigurationResponse).when(emailConfigurationController).getEmailConfigurationByIdAndIdAppAndUserId(1L, 1L);

        ResponseEntity<EmailConfigurationResponse> response = emailConfigurationController.getEmailConfigurationByIdAndIdAppAndUserId(1L, 1L);

        assertEquals(emailConfigurationResponse.getStatusCode(),response.getStatusCode());
        assertEquals(emailConfigurationResponse.getBody(),response.getBody());
    }
}
