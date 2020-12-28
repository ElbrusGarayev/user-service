package com.userservice.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ExceptionEnum {

    USER_NOT_FOUND("1111"),
    USER_ALREADY_EXISTS("1112"),
    USER_VALIDATION_ERROR("1113"),
    WRONG_CREDENTIALS_ERROR("1114"),
    CONFIRMATION_TOKEN_INVALID("1115"),
    CARD_NOT_FOUND("1116"),
    WRONG_CARD_CREDENTIALS("1117"),
    SERVER_ERROR("3000");

    final String code;
}
