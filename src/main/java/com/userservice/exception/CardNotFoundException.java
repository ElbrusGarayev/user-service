package com.userservice.exception;

import com.userservice.enums.ExceptionEnum;

public class CardNotFoundException extends UserBaseException{

    public CardNotFoundException() {
        super(ExceptionEnum.CARD_NOT_FOUND.getCode(), ExceptionEnum.CARD_NOT_FOUND.name().toLowerCase());
    }
}
