package com.notifyforall.api.service.utils;

import com.notifyforall.api.dto.EmailNotificationRequest;
import com.notifyforall.api.model.EmailUserAppConfiguration;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

public class EmailUtil {

    public static void sendmail(EmailNotificationRequest request, EmailUserAppConfiguration emailUserAppConfiguration) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.user", emailUserAppConfiguration.getLoginEmail());
            props.put("mail.smtp.password", emailUserAppConfiguration.getPasswordEmail());
            props.put("mail.smtp.host", emailUserAppConfiguration.getSmtpServiceNameEmail());
            props.put("mail.smtp.port", emailUserAppConfiguration.getPortEmail());

            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(emailUserAppConfiguration.getLoginEmail(),
                            request.getPasswordEmail());
                }
            });
            // Used to debug SMTP issues
            session.setDebug(true);

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(request.getSenderEmail()));

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(request.getRecipientEmail()));
            msg.setSubject(request.getSubjectEmail());
            msg.setContent(request.getTextEmail(), "text/html");
            msg.setSentDate(new Date());

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(request.getTextEmail(), "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            //MimeBodyPart attachPart = new MimeBodyPart();

            //attachPart.attachFile("/var/tmp/image19.png");
            //multipart.addBodyPart(attachPart);
            msg.setContent(multipart);
            Transport.send(msg);
        //} catch (MessagingException | IOException e) {
        } catch (MessagingException e) {

        }
    }
}
