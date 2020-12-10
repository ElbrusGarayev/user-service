package com.userservice.service.impl;

import com.userservice.dto.LoginDTO;
import com.userservice.dto.UserDTO;
import com.userservice.encoder.PasswordEncoder;
import com.userservice.entity.ConfirmationToken;
import com.userservice.entity.User;
import com.userservice.entity.UserLoginHistory;
import com.userservice.exception.ExceptionEnum;
import com.userservice.exception.UserAlreadyExistsException;
import com.userservice.exception.UserNotFoundException;
import com.userservice.exception.WrongCredentialsException;
import com.userservice.jwt.JwtUtil;
import com.userservice.mail.service.EmailService;
import com.userservice.mapper.UserMapper;
import com.userservice.repository.UserLoginHistoryRepository;
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
    final UserLoginHistoryRepository historyRepository;

    static final String SUCCESS = "success";
    static final String SOMETHING_WENT_WRONG = "something_went_wrong";
    static final String CONFIRMATION_URL = "http://localhost:8080/api/user-ms/public/account-confirmation?token=";
    static final String CONFIRMATION_CONTENT = "To confirm your account, please click here : ";

    @Override
    public String login(LoginDTO loginDTO, String ip) {
        User user = userRepository.findByUsername(loginDTO.getUsername()).orElseThrow(WrongCredentialsException::new);
        if (!user.getPassword().equals(PasswordEncoder.sha256(loginDTO.getPassword())) || !user.isEnabled()) {
            throw new WrongCredentialsException();
        }
        String accessToken = jwtUtil.generate(user.getId().toString(), user.getEmail());
        historyRepository.save(new UserLoginHistory(user, accessToken, ip));
        return accessToken;
    }

    @Override
    public UserDTO register(UserDTO userDTO) {
        userRepository.findByUsername(userDTO.getUsername()).ifPresent(ex -> {
            throw new UserAlreadyExistsException(ExceptionEnum.USER_ALREADY_EXISTS);
        });
        userDTO.setPassword(PasswordEncoder.sha256(userDTO.getPassword()));
        User user = userRepository.save(userMapper.dtoToModel(userDTO));
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        tokenService.save(confirmationToken);
        String mailContent = CONFIRMATION_CONTENT + CONFIRMATION_URL + confirmationToken.getConfirmationToken();
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
            return SUCCESS;
        }
        return SOMETHING_WENT_WRONG;
    }
}
