package com.userservice.exception;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserNotFoundException extends UserBaseException {

    public UserNotFoundException() {
        super(ExceptionEnum.USER_NOT_FOUND.getCode(), ExceptionEnum.USER_NOT_FOUND.name().toLowerCase());
    }
}
