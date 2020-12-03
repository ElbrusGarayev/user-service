package com.userservice.service.impl;

import com.userservice.dto.PageAndSizeDTO;
import com.userservice.dto.UserDTO;
import com.userservice.exception.ExceptionEnum;
import com.userservice.exception.UserAlreadyExistsException;
import com.userservice.exception.UserNotFoundException;
import com.userservice.mapper.UserMapper;
import com.userservice.model.User;
import com.userservice.repository.UserRepository;
import com.userservice.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    final UserMapper userMapper;
    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        userRepository.findByEmail(userDTO.getEmail()).ifPresent(t -> {
            throw new UserAlreadyExistsException(ExceptionEnum.USER_ALREADY_EXISTS);
        });
        User user = userMapper.dtoToModel(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.modelToDto(userRepository.save(user));
    }

    @Override
    public List<UserDTO> getAll(PageAndSizeDTO pageAndSizeDTO) {
        Pageable pageable = PageRequest.of(pageAndSizeDTO.getPage(), pageAndSizeDTO.getSize());
        Page<User> page = userRepository.findAll(pageable);
        List<User> users = page.getContent();
        return users.stream().map(userMapper::modelToDto).collect(Collectors.toList());
    }

    @Override
    public UserDTO update(UserDTO userDTO, long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(ExceptionEnum.USER_NOT_FOUND));
        User newUser = userMapper.dtoToModel(userDTO);
        newUser.setId(id);
        newUser.setCreatedDate(user.getCreatedDate());
        return userMapper.modelToDto(userRepository.save(newUser));
    }

    @Override
    public UserDTO findByEmail(String email) {
        return userMapper.modelToDto(userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(ExceptionEnum.USER_NOT_FOUND)));
    }

    @Override
    public void activate(UserDTO userDTO) {
        User user = userMapper.dtoToModel(userDTO);
        userMapper.modelToDto(userRepository.save(user));
    }

}
