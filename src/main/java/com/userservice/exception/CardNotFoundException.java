package com.userservice.exception;

import com.userservice.enums.ExceptionEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

public class CardNotFoundException extends UserBaseException{

    public CardNotFoundException() {
        super(ExceptionEnum.CARD_NOT_FOUND.getCode(), ExceptionEnum.CARD_NOT_FOUND.name().toLowerCase());
    }
}
