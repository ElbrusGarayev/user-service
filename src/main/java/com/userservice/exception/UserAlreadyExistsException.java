package com.userservice.exception;

import com.userservice.enums.ExceptionEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

public class UserAlreadyExistsException extends UserBaseException{

    public UserAlreadyExistsException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getCode(), exceptionEnum.name().toLowerCase());
    }
}
