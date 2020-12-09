package com.userservice.exception;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserNotFoundException extends UserBaseException {

    public UserNotFoundException(String code, String message) {
        super(code, message);
    }

    public UserNotFoundException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getCode(), exceptionEnum.name().toLowerCase());
    }

    public UserNotFoundException() {
        super(ExceptionEnum.USER_NOT_FOUND.getCode(), ExceptionEnum.USER_NOT_FOUND.name().toLowerCase());
    }
}
