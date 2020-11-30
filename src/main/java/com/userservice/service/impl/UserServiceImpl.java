package com.userservice.service.impl;

import com.userservice.dto.UserDTO;
import com.userservice.mapper.UserMapper;
import com.userservice.model.User;
import com.userservice.repository.UserRepository;
import com.userservice.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    final UserMapper userMapper;
    final UserRepository userRepository;

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = userMapper.dtoToModel(userDTO);
        return userMapper.modelToDto(userRepository.save(user));
    }
}
