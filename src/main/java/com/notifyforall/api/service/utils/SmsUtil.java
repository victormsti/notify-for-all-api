package com.notifyforall.api.service.utils;

import com.notifyforall.api.config.exceptions.RestException;
import com.notifyforall.api.dto.SmsNotificationRequest;
import com.notifyforall.api.model.SmsUserAppConfiguration;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.http.HttpStatus;

public class SmsUtil {

    public static void sendSms(SmsNotificationRequest request, SmsUserAppConfiguration smsUserAppConfiguration) {
        try {
            Twilio.init(
                    smsUserAppConfiguration.getLoginSms(),
                    smsUserAppConfiguration.getPasswordSms());

            Message message = Message.creator(
                    new PhoneNumber(request.getSenderPhoneNumber()),
                    new PhoneNumber(request.getReceiverPhoneNumber()),
                    request.getTextSms()
            ).create();

        } catch (RuntimeException e){
            throw new RestException(HttpStatus.BAD_REQUEST,String.format(SystemMessages.COULD_NOT_SEND_TEXT_MESSAGE,e.getMessage()));
        }
    }
}
