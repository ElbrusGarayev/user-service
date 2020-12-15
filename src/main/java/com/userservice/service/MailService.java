package com.userservice.service;

import com.userservice.dto.MailDTO;

import javax.mail.MessagingException;

public interface MailService {

    public void sendMail(MailDTO mailDTO) throws MessagingException;
}
