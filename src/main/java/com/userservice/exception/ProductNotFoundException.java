package com.userservice.exception;

import com.userservice.enums.ExceptionEnum;

public class ProductNotFoundException extends UserBaseException{

    public ProductNotFoundException(){
        super(ExceptionEnum.PRODUCT_NOT_FOUND.getCode(), ExceptionEnum.PRODUCT_NOT_FOUND.name().toLowerCase());
    }
}
