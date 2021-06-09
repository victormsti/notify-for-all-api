package com.notifyforall.api.controller;

import com.notifyforall.api.dto.*;
import com.notifyforall.api.service.NotificationService;
import io.swagger.annotations.Api;
import nl.martijndwars.webpush.Subscription;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/notifications")
@Api(tags = "Notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/web-pushs/applications/{idApplication}/notifications/{idNotification}")
    public ResponseEntity<NotificationResponse> findNotificationDetails(@PathVariable Long idApplication, @PathVariable Long idNotification){
        return ResponseEntity.ok().body(notificationService.findNotificationDetails(idApplication, idNotification));
    }

    @GetMapping("/web-pushs/applications/{idApplication}")
    public ResponseEntity<List<WebPushNotificationResponse>> findAllWebPushNotificationsByIdApp(@PathVariable Long idApplication){
        return ResponseEntity.ok().body(notificationService.findAllWebPushNotificationsByIdApp(idApplication));
    }

    @GetMapping("/emails/applications/{idApplication}")
    public ResponseEntity<List<EmailNotificationResponse>> findAllEmailNotificationsByIdApp(@PathVariable Long idApplication){
        return ResponseEntity.ok().body(notificationService.findAllEmailNotificationsByIdApp(idApplication));
    }

    @GetMapping("/sms/applications/{idApplication}")
    public ResponseEntity<List<SmsNotificationResponse>> findAllSmsNotificationsByIdApp(@PathVariable Long idApplication){
        return ResponseEntity.ok().body(notificationService.findAllSmsNotificationsByIdApp(idApplication));
    }

    @PostMapping(value = "exports/spread-sheets", produces = "text/csv")
    public ResponseEntity exportToSpreadSheet(
            @RequestBody List<NotificationRequest> notifications){
        return ResponseEntity.ok().body(notificationService.exportToSpreadSheet(notifications));
    }

    @PostMapping("/apps/{idApp}/web-push-configurations/{idWebPushConfiguration}/web-pushs/unsubscriptions")
    public ResponseEntity unsubscribeWebPushNotification(@PathVariable Long idApp,
                                                       @PathVariable Long idWebPushConfiguration,
                                                       @RequestParam String endpoint){
        notificationService.unsubscribeWebPushNotification(idApp, idWebPushConfiguration, endpoint);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/apps/{idApp}/web-push-configurations/{idWebPushConfiguration}/web-pushs/subscriptions")
    public ResponseEntity subscribeWebPushNotification(@PathVariable Long idApp,
                                                                             @PathVariable Long idWebPushConfiguration,
                                                                             @RequestBody Subscription request){
        notificationService.subscribeWebPushNotification(idApp, idWebPushConfiguration, request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/apps/{idApp}/web-push-configurations/{idWebPushConfiguration}/web-pushs")
    public ResponseEntity<WebPushNotificationResponse> sendWebPushNotification(@PathVariable Long idApp,
                                                                           @PathVariable Long idWebPushConfiguration,
                                                                           @RequestParam String notificationOrigin,
                                                                           @RequestBody WebPushNotificationRequest request){
        return ResponseEntity.ok().body(notificationService.sendWebPushNotification(idApp, idWebPushConfiguration, notificationOrigin, request));
    }

    @PostMapping("/apps/{idApp}/email-configurations/{idEmailConfiguration}/emails")
    public ResponseEntity<EmailNotificationResponse> sendEmailNotification(@PathVariable Long idApp,
                                                                           @PathVariable Long idEmailConfiguration,
                                                                           @RequestParam String notificationOrigin,
                                                                           @RequestBody EmailNotificationRequest request){
        return ResponseEntity.ok().body(notificationService.sendEmailNotification(idApp, idEmailConfiguration, notificationOrigin, request));
    }

    @PostMapping("/apps/{idApp}/sms-configurations/{idSmsConfiguration}/sms")
    public ResponseEntity<SmsNotificationResponse> sendSmsNotification(@PathVariable Long idApp,
                                                                       @PathVariable Long idSmsConfiguration,
                                                                       @RequestParam String notificationOrigin,
                                                                       @RequestBody SmsNotificationRequest request){
        return ResponseEntity.ok().body(notificationService.sendSmsNotification(idApp, idSmsConfiguration, notificationOrigin, request));
    }

}
