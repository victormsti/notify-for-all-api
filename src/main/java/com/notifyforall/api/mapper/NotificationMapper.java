package com.notifyforall.api.mapper;

import com.notifyforall.api.dto.*;
import com.notifyforall.api.model.EmailUserAppConfiguration;
import com.notifyforall.api.model.Notification;
import com.notifyforall.api.model.SmsUserAppConfiguration;
import org.mapstruct.*;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface NotificationMapper {

    NotificationResponse toResponse(Notification notification);
    EmailNotificationResponse toEmailResponse(Notification notification);
    List<EmailNotificationResponse> toEmailResponseList(List<Notification> emailUserAppConfigurationList);
    SmsNotificationResponse toSmsResponse(Notification notification);
    List<SmsNotificationResponse> toSmsResponseList(List<Notification> smsUserAppConfigurationList);
    WebPushNotificationResponse toWebPushResponse(Notification notification);
    List<WebPushNotificationResponse> toWebPushResponseList(List<Notification> smsUserAppConfigurationList);

    @InheritInverseConfiguration
    Notification fromEmailRequest(EmailNotificationRequest request);
    @InheritInverseConfiguration
    Notification fromSmsRequest(SmsNotificationRequest request);

    Notification copy(Notification copy, @MappingTarget Notification target);

}
