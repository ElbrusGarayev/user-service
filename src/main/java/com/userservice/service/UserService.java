package com.userservice.service;

import com.userservice.dto.PageAndSizeDTO;
import com.userservice.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAll(PageAndSizeDTO pageAndSizeDTO);
    UserDTO update(UserDTO userDTO, Long id);
    void deleteUsers();
}
