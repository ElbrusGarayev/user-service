package com.userservice.exception;

import com.userservice.enums.ExceptionEnum;

public class NotEnoughProductException extends UserBaseException{

    public NotEnoughProductException() {
        super(ExceptionEnum.NOT_ENOUGH_PRODUCT.getCode(), ExceptionEnum.NOT_ENOUGH_PRODUCT.name().toLowerCase());
    }
}
