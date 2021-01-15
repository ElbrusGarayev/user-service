package com.userservice.service;

import com.userservice.dto.LoginDTO;
import com.userservice.dto.UserDTO;

import javax.mail.MessagingException;
import javax.naming.ConfigurationException;
import java.io.UnsupportedEncodingException;

public interface AuthService {
    String login(LoginDTO loginDTO, String ip);

    UserDTO register(UserDTO userDTO) throws MessagingException, UnsupportedEncodingException;

    void activate(String token);
}
