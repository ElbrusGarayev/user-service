package com.userservice.exception;

import com.userservice.enums.ExceptionEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

public class ConfirmationTokenInvalidException extends UserBaseException{

    public ConfirmationTokenInvalidException() {
        super(ExceptionEnum.CONFIRMATION_TOKEN_INVALID.getCode(), ExceptionEnum.CONFIRMATION_TOKEN_INVALID.name().toLowerCase());
    }
}
