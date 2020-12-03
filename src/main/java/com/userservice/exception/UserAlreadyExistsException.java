package com.userservice.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAlreadyExistsException extends UserBaseException{

    public UserAlreadyExistsException(String code, String message) {
        super(code, message);
    }

    public UserAlreadyExistsException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getCode(), exceptionEnum.name().toLowerCase());
    }
}
