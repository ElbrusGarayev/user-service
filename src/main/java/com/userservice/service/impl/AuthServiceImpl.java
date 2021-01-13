package com.userservice.service.impl;

import com.userservice.dto.LoginDTO;
import com.userservice.dto.MailDTO;
import com.userservice.dto.UserDTO;
import com.userservice.encoder.PasswordEncoder;
import com.userservice.entity.ConfirmationToken;
import com.userservice.entity.User;
import com.userservice.entity.UserLoginHistory;
import com.userservice.enums.ExceptionEnum;
import com.userservice.exception.*;
import com.userservice.jwt.JwtUtil;
import com.userservice.mapper.UserMapper;
import com.userservice.repository.UserLoginHistoryRepository;
import com.userservice.repository.UserRepository;
import com.userservice.service.AuthService;
import com.userservice.service.ConfirmationTokenService;
import com.userservice.service.MailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthServiceImpl implements AuthService {

    static final String CONFIRMATION_URL = "http://localhost:8080/api/user-ms/public/account-confirmation?token=";
    static final String CONFIRMATION_CONTENT = "To confirm your account, please click here : ";

    final UserRepository userRepository;
    final JwtUtil jwtUtil;
    final UserMapper userMapper;
    final MailService emailService;
    final ConfirmationTokenService tokenService;
    final UserLoginHistoryRepository historyRepository;

    @Override
    public String login(LoginDTO loginDTO, String ip) {
        User user = userRepository.findByUsername(loginDTO.getUsername()).orElseThrow(WrongCredentialsException::new);
        if (!user.getPassword().equals(PasswordEncoder.sha256(loginDTO.getPassword())) || !user.isEnabled()) {
            throw new WrongCredentialsException();
        }
        String accessToken = jwtUtil.generate(user.getId().toString(), user.getEmail());
        historyRepository.save(UserLoginHistory.builder()
                .ip(ip)
                .accessToken(accessToken)
                .user(user)
                .loginDateTime(LocalDateTime.now())
                .build());
        return accessToken;
    }

    @Override
    public UserDTO register(UserDTO userDTO) throws MessagingException {
        userRepository.findByUsername(userDTO.getUsername()).ifPresent(ex -> {
            throw new UserAlreadyExistsException(ExceptionEnum.USER_ALREADY_EXISTS);
        });
        userDTO.setPassword(PasswordEncoder.sha256(userDTO.getPassword()));
        User user = userRepository.save(userMapper.dtoToModel(userDTO));
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        log.warn("Transaction is open?  " + TransactionSynchronizationManager.isActualTransactionActive());
        tokenService.save(confirmationToken);
        log.warn("Transaction is open?  " + TransactionSynchronizationManager.isActualTransactionActive());
        String mailContent = CONFIRMATION_CONTENT + CONFIRMATION_URL + confirmationToken.getToken();
        emailService.sendMail(MailDTO.builder()
                .to(userDTO.getEmail())
                .subject("Email Confirmation")
                .content(mailContent)
                .build());
        return userMapper.modelToDto(user);
    }

    @Override
    public void activate(String token) {
        ConfirmationToken confirmationToken = tokenService.findToken(token).orElseThrow(ConfirmationTokenInvalidException::new);
        User user = userRepository.findByEmail(confirmationToken.getUser().getEmail()).orElseThrow(UserNotFoundException::new);
        user.setEnabled(true);
        userRepository.save(user);
    }
}
