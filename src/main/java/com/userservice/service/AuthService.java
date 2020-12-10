package com.userservice.service;

import com.userservice.dto.LoginDTO;
import com.userservice.dto.UserDTO;

public interface AuthService {
    String login(LoginDTO loginDTO, String ip);

    UserDTO register(UserDTO userDTO);

    String activate(String token);
}
