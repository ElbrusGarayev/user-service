package com.userservice.service.impl;

import com.userservice.dto.MailDTO;
import com.userservice.service.MailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MailServiceImpl implements MailService {

    final JavaMailSender mailSender;

    @Override
    public void sendMail(MailDTO mailDTO) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.setFrom(new InternetAddress("", "User Service"));
        helper.setTo(mailDTO.getTo());
        helper.setSubject(mailDTO.getSubject());
        helper.setText(mailDTO.getContent());
        mailSender.send(message);
    }
}
