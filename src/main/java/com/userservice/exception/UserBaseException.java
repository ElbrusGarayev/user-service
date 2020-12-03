package com.userservice.exception;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserBaseException extends RuntimeException{

    String code;
    String message;
    LocalDateTime dateTime;

    public UserBaseException(String code, String message){
        this.code = code;
        this.message = message;
        this.dateTime = LocalDateTime.now();
    }
}
