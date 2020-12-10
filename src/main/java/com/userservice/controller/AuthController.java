package com.userservice.controller;

import com.userservice.dto.LoginDTO;
import com.userservice.dto.UserDTO;
import com.userservice.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthController {

    final AuthService authService;

    @PostMapping("public/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        return ResponseEntity.ok(authService.login(loginDTO, request.getRemoteAddr()));
    }

    @PostMapping("public/registration")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(authService.register(userDTO));
    }

    @GetMapping("public/account-confirmation")
    public ResponseEntity<String> confirmUserAccount(@RequestParam("token") String token) {
        return ResponseEntity.ok(authService.activate(token));
    }
}
