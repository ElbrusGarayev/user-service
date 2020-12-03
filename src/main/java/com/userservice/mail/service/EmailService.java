package com.userservice.mail.service;

import com.userservice.mail.model.EmailStatus;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Log4j2
@Service
@AllArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    public EmailStatus sendMail(String to, String text) {
        String subject = "Confirmation email";
        try {
            InternetAddress addressFrom = new InternetAddress("info.overbrain", "GARA");
            MimeMessage mail = javaMailSender.createMimeMessage();

            mail.setFrom(addressFrom);
            mail.setSender(addressFrom);
            mail.setSubject(subject);
            mail.setContent(text, "text/plain");
            mail.addRecipients(Message.RecipientType.TO, to);
            javaMailSender.send(mail);

            log.info("Send email '{}' to: {}", subject, to);

            return new EmailStatus(to, subject, text).success();

        } catch (Exception e) {

            log.warn(String.format("Problem with sending email to: %s, error message: %s", to, e.getMessage()));

            return new EmailStatus(to, subject, text).error(e.getMessage());
        }
    }
}
