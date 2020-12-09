package com.userservice.service.impl;

import com.userservice.dto.LoginDTO;
import com.userservice.dto.UserDTO;
import com.userservice.encoder.PasswordEncoder;
import com.userservice.entity.ConfirmationToken;
import com.userservice.entity.User;
import com.userservice.exception.UserNotFoundException;
import com.userservice.jwt.JwtUtil;
import com.userservice.mail.service.EmailService;
import com.userservice.mapper.UserMapper;
import com.userservice.repository.UserRepository;
import com.userservice.service.AuthService;
import com.userservice.service.ConfirmationTokenService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthServiceImpl implements AuthService {

    final UserRepository userRepository;
    final JwtUtil jwtUtil;
    final UserMapper userMapper;
    final EmailService emailService;
    final ConfirmationTokenService tokenService;

    static final String CONFIRMATION_URL = "http://localhost:8080/api/user-ms/public/account-confirmation?token=";
    static final String CONFIRMATION_CONTENT= "To confirm your account, please click here : ";

    @Override
    public String login(LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername()).orElseThrow(UserNotFoundException::new);
        if (!user.getPassword().equals(PasswordEncoder.sha256(loginDTO.getPassword()))) {
            throw new UserNotFoundException();
        }
        return jwtUtil.generate(user.getId().toString(), user.getEmail());
    }

    @Override
    public UserDTO register(UserDTO userDTO) {
        userDTO.setPassword(PasswordEncoder.sha256(userDTO.getPassword()));
        User user = userRepository.save(userMapper.dtoToModel(userDTO));
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        tokenService.save(confirmationToken);
        String mailContent = CONFIRMATION_CONTENT
                + CONFIRMATION_URL + confirmationToken.getConfirmationToken();
        emailService.sendMail(user.getEmail(), mailContent);
        return userMapper.modelToDto(user);
    }

    @Override
    public String activate(String token) {
        Optional<ConfirmationToken> confirmationToken = tokenService.findToken(token);
        if (confirmationToken.isPresent()) {
            User user = userRepository.findByEmail(confirmationToken.get().getUser().getEmail()).orElseThrow(UserNotFoundException::new);
            user.setEnabled(true);
            userRepository.save(user);
            return "success";
        }
        return "something_went_wrong";
    }
}
