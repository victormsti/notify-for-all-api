package com.notifyforall.api.controller;

import com.notifyforall.api.dto.AppRequest;
import com.notifyforall.api.dto.AppResponse;
import com.notifyforall.api.dto.EnableOrDisableNotificationRequest;
import com.notifyforall.api.service.AppService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/applications")
@Api(tags = "Applications")
public class AppController {

    private final AppService appService;

    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppResponse> findAppById(@PathVariable Long id){
        return ResponseEntity.ok().body(appService.findAppById(id));
    }

    @PostMapping("records")
    public ResponseEntity<AppResponse> registerApp(@RequestBody AppRequest aplicativo){
        return ResponseEntity.ok().body(appService.registerApp(aplicativo));
    }

    @PatchMapping("{id}/updates/web-pushs")
    public ResponseEntity enableOrDisableWebPushNotification(@PathVariable Long id,
                                                             @RequestBody EnableOrDisableNotificationRequest request){
        appService.enableOrDisableWebPushNotification(id, request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{id}/updates/emails")
    public ResponseEntity enableOrDisableEmailNotification(@PathVariable Long id,
                                                           @RequestBody EnableOrDisableNotificationRequest request){
        appService.enableOrDisableEmailNotification(id, request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{id}/updates/sms")
    public ResponseEntity enableOrDisableSmsNotification(@PathVariable Long id,
                                                           @RequestBody EnableOrDisableNotificationRequest request){
        appService.enableOrDisableSmsNotification(id, request);
        return ResponseEntity.ok().build();
    }

}
