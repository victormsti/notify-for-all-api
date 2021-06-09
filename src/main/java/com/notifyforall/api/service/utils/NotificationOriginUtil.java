package com.notifyforall.api.service.utils;

import com.notifyforall.api.config.exceptions.RestException;
import com.notifyforall.api.enums.NotificationOrigin;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class NotificationOriginUtil {

    public static NotificationOrigin validateAndSetNotificationOrigin(String notificationOrigin){
        if(notificationOrigin != null && Arrays.stream(NotificationOrigin.values()).noneMatch(e->e.getValue().equalsIgnoreCase(notificationOrigin))){
            throw new RestException(HttpStatus.BAD_REQUEST,String.format(SystemMessages.INVALID_NOTIFICATION_ORIGIN, Arrays.asList(NotificationOrigin.values()).toString()));
        }

        return Arrays.stream(NotificationOrigin.values())
                .filter(e->e.getValue().equalsIgnoreCase(notificationOrigin))
                .findAny()
                .orElse(null);
    }
}
