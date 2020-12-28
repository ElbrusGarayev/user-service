package com.userservice.service;

import com.userservice.dto.OrderBodyDTO;
import com.userservice.dto.OrderDTO;
import com.userservice.dto.PageAndSizeDTO;
import com.userservice.dto.UserDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {
    List<UserDTO> getAll(PageAndSizeDTO pageAndSizeDTO);
    UserDTO update(UserDTO userDTO, Long id);
    void deleteUsers();

    OrderDTO purchaseProduct(OrderBodyDTO orderBodyDTO, Authentication auth);
}
