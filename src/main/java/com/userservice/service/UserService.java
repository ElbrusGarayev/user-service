package com.userservice.service;

import com.userservice.dto.PageAndSizeDTO;
import com.userservice.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO addUser(UserDTO userDTO);
    List<UserDTO> getAll(PageAndSizeDTO pageAndSizeDTO);
    UserDTO update(UserDTO userDTO, long id);
    UserDTO findByEmail(String email);
    void activate(UserDTO userDTO);
}
