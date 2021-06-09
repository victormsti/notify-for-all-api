package com.notifyforall.api.controller;

import com.notifyforall.api.dto.EmailConfigurationRequest;
import com.notifyforall.api.dto.EmailConfigurationResponse;
import com.notifyforall.api.service.EmailConfigurationService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/email-configurations")
@Api(tags = "Email Configurations")
public class EmailConfigurationController {

    private final EmailConfigurationService emailConfigurationService;

    public EmailConfigurationController(EmailConfigurationService emailConfigurationService) {
        this.emailConfigurationService = emailConfigurationService;
    }

    @GetMapping("{id}/apps/{idApp}")
    public ResponseEntity<EmailConfigurationResponse> getEmailConfigurationByIdAndIdAppAndUserId(@PathVariable Long id, @PathVariable Long idApp){
        return ResponseEntity.ok().body(emailConfigurationService.getEmailConfigurationByIdAndIdAppAndUserId(id, idApp));
    }

    @PostMapping("records/apps/{idApp}")
    public ResponseEntity<EmailConfigurationResponse> registerNewEmailConfiguration(@PathVariable Long idApp,
                                                                                    @RequestBody EmailConfigurationRequest request){
        return ResponseEntity.ok().body(emailConfigurationService.registerNewEmailConfiguration(idApp, request));
    }

}
