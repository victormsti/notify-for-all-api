package com.notifyforall.api.service;

import com.notifyforall.api.config.context.UserContext;
import com.notifyforall.api.config.exceptions.RestException;
import com.notifyforall.api.dto.*;
import com.notifyforall.api.enums.ChannelType;
import com.notifyforall.api.mapper.NotificationMapper;
import com.notifyforall.api.model.EmailUserAppConfiguration;
import com.notifyforall.api.model.Notification;
import com.notifyforall.api.model.SmsUserAppConfiguration;
import com.notifyforall.api.model.WebPushUserAppConfiguration;
import com.notifyforall.api.repository.*;
import com.notifyforall.api.service.utils.*;
import nl.martijndwars.webpush.Subscription;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final AppRepository appRepository;
    private final WebPushConfigurationRepository webPushConfigurationRepository;
    private final EmailConfigurationRepository emailConfigurationRepository;
    private final SmsConfigurationRepository smsConfigurationRepository;
    private final WebPushService webPushService;
    private final PasswordEncoder passwordEncoder;
    private final NotificationMapper mapper;

    public NotificationService(NotificationRepository notificationRepository,
                               AppRepository appRepository,
                               WebPushConfigurationRepository webPushConfigurationRepository,
                               EmailConfigurationRepository emailConfigurationRepository,
                               SmsConfigurationRepository smsConfigurationRepository,
                               WebPushService webPushService,
                               PasswordEncoder passwordEncoder,
                               NotificationMapper mapper) {
        this.notificationRepository = notificationRepository;
        this.appRepository = appRepository;
        this.webPushConfigurationRepository = webPushConfigurationRepository;
        this.emailConfigurationRepository = emailConfigurationRepository;
        this.smsConfigurationRepository = smsConfigurationRepository;
        this.webPushService = webPushService;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    public NotificationResponse findNotificationDetails(Long idApplication, Long idNotification){

        return mapper.toResponse(notificationRepository.findByIdAndAppIdAndAppUserAppId(
                idNotification, idApplication, UserContext.getInstance().getUser().getId()));
    }

    public List<WebPushNotificationResponse> findAllWebPushNotificationsByIdApp(Long idApplication){

        return mapper.toWebPushResponseList(notificationRepository.findByAppIdAndChannelTypeAndAppUserAppId(
                idApplication, ChannelType.WEB_PUSH, UserContext.getInstance().getUser().getId()));
    }

    public List<EmailNotificationResponse> findAllEmailNotificationsByIdApp(Long idApplication){
        return mapper.toEmailResponseList(notificationRepository.findByAppIdAndChannelTypeAndAppUserAppId(
                idApplication, ChannelType.EMAIL, UserContext.getInstance().getUser().getId()));
    }

    public List<SmsNotificationResponse> findAllSmsNotificationsByIdApp(Long idApplication){
        return mapper.toSmsResponseList(notificationRepository.findByAppIdAndChannelTypeAndAppUserAppId(
                idApplication, ChannelType.SMS, UserContext.getInstance().getUser().getId()));
    }

    public ResponseEntity exportToSpreadSheet(List<NotificationRequest> notifications){
        return CSVUtil.exportCSV(notifications);
    }

    public void subscribeWebPushNotification(Long idApp, Long idWebPushConfiguration, Subscription request){
        Optional<WebPushUserAppConfiguration> optionalWebPushUserAppConfiguration = webPushConfigurationRepository.findByIdAndAppIdAndAppUserAppId(
                idWebPushConfiguration, idApp, UserContext.getInstance().getUser().getId());

        if(optionalWebPushUserAppConfiguration.isEmpty()){
            throw new RestException(HttpStatus.BAD_REQUEST,String.format(SystemMessages.WEB_PUSH_NOT_FOUND_BY_ID_AND_ID_APP, idWebPushConfiguration, idApp));
        }

        webPushService.subscribe(request);
    }

    public void unsubscribeWebPushNotification(Long idApp, Long idWebPushConfiguration, String endpoint){
        Optional<WebPushUserAppConfiguration> optEmailUserAppConfiguration = webPushConfigurationRepository.findByIdAndAppIdAndAppUserAppId(
                idWebPushConfiguration, idApp, UserContext.getInstance().getUser().getId());

        if(optEmailUserAppConfiguration.isEmpty()){
            throw new RestException(HttpStatus.BAD_REQUEST,String.format(SystemMessages.WEB_PUSH_NOT_FOUND_BY_ID_AND_ID_APP, idWebPushConfiguration, idApp));
        }

        webPushService.unsubscribe(endpoint);
    }

    public WebPushNotificationResponse sendWebPushNotification(Long idApp, Long idWebPushConfiguration, String notificationOrigin, WebPushNotificationRequest request){
        Notification notification = new Notification();

        Optional<WebPushUserAppConfiguration> optionalWebPushUserAppConfiguration = webPushConfigurationRepository.findByIdAndAppIdAndAppUserAppId(
                idWebPushConfiguration, idApp, UserContext.getInstance().getUser().getId());

        if(optionalWebPushUserAppConfiguration.isEmpty()){
            throw new RestException(HttpStatus.BAD_REQUEST,String.format(SystemMessages.WEB_PUSH_NOT_FOUND_BY_ID_AND_ID_APP, idWebPushConfiguration, idApp));
        }

        notification.setChannelType(ChannelType.WEB_PUSH);
        notification.setNotificationOrigin(NotificationOriginUtil.validateAndSetNotificationOrigin(notificationOrigin));
        notification.setReadConfirmation(false);
        notification.setApp(appRepository.findByIdAndUserAppId(idApp,UserContext.getInstance().getUser().getId()).get());
        notification.setWebPushUserAppConfiguration(optionalWebPushUserAppConfiguration.get());

        webPushService.sendNotification(webPushService.getSubscriptions(),request.getMessage());

        notification.setSendDate(LocalDate.now());
        return mapper.toWebPushResponse(notificationRepository.save(notification));
    }

    public EmailNotificationResponse sendEmailNotification(Long idApp, Long idEmailConfiguration, String notificationOrigin, EmailNotificationRequest request){
        Notification notification = mapper.fromEmailRequest(request);

        Optional<EmailUserAppConfiguration> optEmailUserAppConfiguration = emailConfigurationRepository.findByIdAndAppIdAndAppUserAppId(
                idEmailConfiguration, idApp, UserContext.getInstance().getUser().getId());

        if(optEmailUserAppConfiguration.isEmpty()){
            throw new RestException(HttpStatus.BAD_REQUEST,String.format(SystemMessages.EMAIL_NOT_FOUND_BY_ID_AND_ID_APP,idEmailConfiguration, idApp));
        }

        notification.setChannelType(ChannelType.EMAIL);
        notification.setNotificationOrigin(NotificationOriginUtil.validateAndSetNotificationOrigin(notificationOrigin));
        notification.setReadConfirmation(false);
        notification.setApp(appRepository.findByIdAndUserAppId(idApp,UserContext.getInstance().getUser().getId()).get());
        notification.setEmailUserAppConfiguration(optEmailUserAppConfiguration.get());

        if(!notification.getApp().getEnableNotificationEmail()){
            throw new RestException(HttpStatus.FORBIDDEN,String.format(SystemMessages.EMAIL_NOT_FOUND_BY_ID_AND_ID_APP,idEmailConfiguration, idApp));
        }

        EmailUtil.sendmail(request, optEmailUserAppConfiguration.get());

        notification.setSendDate(LocalDate.now());
        return mapper.toEmailResponse(notificationRepository.save(notification));
    }

    public SmsNotificationResponse sendSmsNotification(Long idApp, Long idSmsConfiguration, String notificationOrigin, SmsNotificationRequest request){
        Notification notification = mapper.fromSmsRequest(request);

        Optional<SmsUserAppConfiguration> optSmsUserAppConfiguration = smsConfigurationRepository.findByIdAndAppIdAndAppUserAppId(
                idSmsConfiguration, idApp, UserContext.getInstance().getUser().getId());

        if(optSmsUserAppConfiguration.isEmpty()){
            throw new RestException(HttpStatus.BAD_REQUEST,String.format(SystemMessages.APP_NOT_ENABLED_TO_SEND_EMAIL, idApp));
        }

        notification.setNotificationOrigin(NotificationOriginUtil.validateAndSetNotificationOrigin(notificationOrigin));
        notification.setChannelType(ChannelType.SMS);
        notification.setReadConfirmation(false);
        notification.setApp(appRepository.findByIdAndUserAppId(idApp, UserContext.getInstance().getUser().getId()).get());
        notification.setSmsUserAppConfiguration(optSmsUserAppConfiguration.get());

        if(!notification.getApp().getEnableNotificationSms()){
            throw new RestException(HttpStatus.FORBIDDEN,String.format(SystemMessages.APP_NOT_ENABLED_TO_SEND_SMS,idApp));
        }

        SmsUtil.sendSms(request, optSmsUserAppConfiguration.get());

        notification.setSendDate(LocalDate.now());
        return mapper.toSmsResponse(notificationRepository.save(notification));
    }

}
