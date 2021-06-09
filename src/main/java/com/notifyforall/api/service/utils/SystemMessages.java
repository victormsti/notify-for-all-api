package com.notifyforall.api.service.utils;

public class SystemMessages {

    //USER
    public static final String USER_NOT_FOUND_BY_ID = "User not found by id: %s";
    public static final String USER_ALREADY_EXISTS_BY_USERNAME = "User already exists with username: %s";
    public static final String USER_ALREADY_EXISTS_BY_EMAIL = "User already exists with e-mail: : %s";

    //APP
    public static final String APP_NOT_FOUND_BY_ID = "App not found by id: %s";
    public static final String APP_ALREADY_EXISTS_BY_IDENTIFIER_NAME = "User app already exists with identifier name: %s";
    public static final String APP_NOT_ENABLED_TO_SEND_EMAIL = "App with id: %s not enabled to send e-mail";
    public static final String APP_NOT_ENABLED_TO_SEND_SMS = "App with id: %s not enabled to send Text Message";

    //WEB PUSH
    public static final String WEB_PUSH_NOT_FOUND_BY_ID_AND_ID_APP = "App not found by logged user using " +
            "id web push configuration: %s and id app %s";

    //E-MAIL
    public static final String EMAIL_NOT_FOUND_BY_ID_AND_ID_APP = "App not found by logged user using " +
            "id email configuration: %s and id app %s";

    //SMS
    public static final String SMS_NOT_FOUND_BY_ID_AND_ID_APP = "App not found by logged user using " +
            "id sms configuration: %s and id app %s";
    public static final String COULD_NOT_SEND_TEXT_MESSAGE = "Could not send Text Message: %s";

    //GENERAL
    public static final String INVALID_NOTIFICATION_ORIGIN = "Notification Origin needs to be one of these: %s";
}
