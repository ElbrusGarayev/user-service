package com.userservice.exception;

import com.userservice.enums.ExceptionEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

public class UserNotFoundException extends UserBaseException {

    public UserNotFoundException() {
        super(ExceptionEnum.USER_NOT_FOUND.getCode(), ExceptionEnum.USER_NOT_FOUND.name().toLowerCase());
    }
}
