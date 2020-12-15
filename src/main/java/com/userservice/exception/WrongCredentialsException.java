package com.userservice.exception;

import com.userservice.enums.ExceptionEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WrongCredentialsException extends UserBaseException {

    public WrongCredentialsException() {
        super(ExceptionEnum.WRONG_CREDENTIALS_ERROR.getCode(), ExceptionEnum.WRONG_CREDENTIALS_ERROR.name().toLowerCase());
    }
}
