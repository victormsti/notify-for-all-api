package com.notifyforall.api.controller;

import com.notifyforall.api.dto.SmsConfigurationRequest;
import com.notifyforall.api.dto.SmsConfigurationResponse;
import com.notifyforall.api.service.SmsConfigurationService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/sms-configurations")
@Api(tags = "SMS Configurations")
public class SmsConfigurationController {

    private final SmsConfigurationService smsConfigurationService;

    public SmsConfigurationController(SmsConfigurationService smsConfigurationService) {
        this.smsConfigurationService = smsConfigurationService;
    }

    @GetMapping("{id}/apps/{idApp}")
    public ResponseEntity<SmsConfigurationResponse> getSmsConfigurationByIdAndIdAppAndUserId(@PathVariable Long id, @PathVariable Long idApp){
        return ResponseEntity.ok().body(smsConfigurationService.getSmsConfigurationByIdAndIdAppAndUserId(id, idApp));
    }

    @PostMapping("records/apps/{idApp}")
    public ResponseEntity<SmsConfigurationResponse> registerNewSmsConfiguration(@PathVariable Long idApp,
                                                                                @RequestBody SmsConfigurationRequest request){
        return ResponseEntity.ok().body(smsConfigurationService.registerNewSmsConfiguration(idApp, request));
    }

}
