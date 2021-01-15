package com.userservice.controller;

import com.userservice.dto.LoginDTO;
import com.userservice.dto.UserDTO;
import com.userservice.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@Log4j2
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("public")
public class AuthController {

    final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO, String ip) {
        return ResponseEntity.ok(authService.login(loginDTO, ip));
    }

    @PostMapping("registration")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO userDTO) throws MessagingException, UnsupportedEncodingException {
        log.warn("Transaction is open?  " + TransactionSynchronizationManager.isActualTransactionActive());
        return ResponseEntity.ok(authService.register(userDTO));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping("account-confirmation")
    public void confirmUserAccount(@RequestParam String token) {
        authService.activate(token);
    }
}
