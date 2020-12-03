package com.userservice.token.service;

import com.userservice.token.model.ConfirmationToken;

import java.util.Optional;

public interface ConfirmationTokenService {

    Optional<ConfirmationToken> findToken(String token);
    void save(ConfirmationToken token);
}
