package com.userservice.service.impl;

import com.userservice.dto.PageAndSizeDTO;
import com.userservice.dto.UserDTO;
import com.userservice.entity.User;
import com.userservice.exception.UserNotFoundException;
import com.userservice.mapper.UserMapper;
import com.userservice.repository.UserRepository;
import com.userservice.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    final UserMapper userMapper;
    final UserRepository userRepository;

    @Override
    public List<UserDTO> getAll(PageAndSizeDTO pageAndSizeDTO) {
        Pageable pageable = PageRequest.of(pageAndSizeDTO.getPage(), pageAndSizeDTO.getSize());
        Page<User> page = userRepository.findAll(pageable);
        List<User> users = page.getContent();
        return users.stream().map(userMapper::modelToDto).collect(Collectors.toList());
    }

    @Override
    public UserDTO update(UserDTO userDTO, Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        User newUser = userMapper.dtoToModel(userDTO, user);
        return userMapper.modelToDto(userRepository.save(newUser));
    }

    @Override
    public void deleteUsers() {
        userRepository.deleteAll();
    }

}
