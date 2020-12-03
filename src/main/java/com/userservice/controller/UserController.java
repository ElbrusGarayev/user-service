package com.userservice.controller;

import com.userservice.dto.PageAndSizeDTO;
import com.userservice.dto.UserDTO;
import com.userservice.mail.service.EmailService;
import com.userservice.mapper.UserMapper;
import com.userservice.model.User;
import com.userservice.security.jwt.model.AuthRequest;
import com.userservice.security.jwt.util.JwtUtil;
import com.userservice.service.UserService;
import com.userservice.token.model.ConfirmationToken;
import com.userservice.token.service.ConfirmationTokenService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Log4j2
@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {

    final UserService userService;
    final ConfirmationTokenService tokenService;
    final UserMapper userMapper;
    final EmailService emailService;
    final AuthenticationManager authenticationManager;
    final JwtUtil jwtUtil;

    @GetMapping("all")
    public ResponseEntity<List<UserDTO>> getUsers(@Valid PageAndSizeDTO pageAndSizeDTO) {
        return ResponseEntity.ok(userService.getAll(pageAndSizeDTO));
    }

    @GetMapping("confirm-account")
    public ResponseEntity<String> confirmUserAccount(@RequestParam("token") String token) {
        Optional<ConfirmationToken> confirmationToken = tokenService.findToken(token);
        if (confirmationToken.isPresent()) {
            UserDTO user = userService.findByEmail(confirmationToken.get().getUser().getEmail());
            user.setEnabled(true);
            userService.activate(user);
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.ok("something_went_wrong");
    }

    @PostMapping("registration")
    public ResponseEntity<String> save(@Valid @RequestBody UserDTO userDTO) {
        User user = userMapper.dtoToModel(userService.addUser(userDTO));
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        tokenService.save(confirmationToken);

        String mailContent = "To confirm your account, please click here : "
                + "http://localhost:8080/api/user-ms/confirm-account?token=" + confirmationToken.getConfirmationToken();
        emailService.sendMail(user.getEmail(), mailContent);
        return ResponseEntity.ok("Success");
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO userDTO, @PathVariable long id) {
        return ResponseEntity.ok(userService.update(userDTO, id));
    }

    @PostMapping("authentication")
    public ResponseEntity<String> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception ex){
            throw new Exception("invalid_username_or_password");
        }
        return ResponseEntity.ok(jwtUtil.generateToken(authRequest.getUsername()));
    }
}
