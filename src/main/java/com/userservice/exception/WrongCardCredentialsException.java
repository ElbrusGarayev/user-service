package com.userservice.exception;

import com.userservice.enums.ExceptionEnum;

public class WrongCardCredentialsException extends UserBaseException{

    public WrongCardCredentialsException() {
        super(ExceptionEnum.WRONG_CARD_CREDENTIALS.getCode(), ExceptionEnum.WRONG_CARD_CREDENTIALS.name().toLowerCase());
    }
}
