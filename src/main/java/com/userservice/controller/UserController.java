package com.userservice.controller;

import com.userservice.dto.PageAndSizeDTO;
import com.userservice.dto.UserDTO;
import com.userservice.jwt.JwtUser;
import com.userservice.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {

    final UserService userService;

    @GetMapping("public/delete")
    public String deleteUser(){
        userService.deleteUsers();
        return "deleted";
    }

    @GetMapping("all")
    public ResponseEntity<List<UserDTO>> getUsers(@Valid PageAndSizeDTO pageAndSizeDTO) {
        return ResponseEntity.ok(userService.getAll(pageAndSizeDTO));
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO userDTO, Authentication auth) {
        JwtUser jwtUser = (JwtUser) auth.getPrincipal();
        return ResponseEntity.ok(userService.update(userDTO, jwtUser.getId()));
    }
}
