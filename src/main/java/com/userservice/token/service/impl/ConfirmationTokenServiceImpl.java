package com.userservice.token.service.impl;

import com.userservice.token.model.ConfirmationToken;
import com.userservice.token.repository.ConfirmationTokenRepository;
import com.userservice.token.service.ConfirmationTokenService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    final ConfirmationTokenRepository tokenRepository;

    @Override
    public Optional<ConfirmationToken> findToken(String token) {
        return tokenRepository.findByConfirmationToken(token);
    }

    @Override
    public void save(ConfirmationToken token) {
        tokenRepository.save(token);
    }
}
