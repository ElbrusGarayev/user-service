package com.userservice.service;

import com.userservice.entity.ConfirmationToken;

import java.util.Optional;

public interface ConfirmationTokenService {

    Optional<ConfirmationToken> findToken(String token);
    void save(ConfirmationToken token);
}
