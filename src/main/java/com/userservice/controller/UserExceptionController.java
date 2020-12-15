package com.userservice.controller;

import com.userservice.dto.ErrorDTO;
import com.userservice.enums.ExceptionEnum;
import com.userservice.exception.ConfirmationTokenInvalidException;
import com.userservice.exception.UserAlreadyExistsException;
import com.userservice.exception.UserNotFoundException;
import com.userservice.exception.WrongCredentialsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class UserExceptionController {

    final

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorDTO handleException(UserNotFoundException ex){
        log.error ("User not found exception ", ex.getMessage());
        return ErrorDTO.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .dateTime(ex.getDateTime())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ErrorDTO handleException(UserAlreadyExistsException ex){
        log.error ("User already exists exception", ex.getMessage());
        return ErrorDTO.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .dateTime(ex.getDateTime())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Object handleException(MethodArgumentNotValidException ex) {
        log.error ("Method argument not valid exception", ex.getMessage());
        FieldError fieldError = ex.getBindingResult().getFieldError();
        return ErrorDTO
                .builder()
                .code(ExceptionEnum.USER_VALIDATION_ERROR.getCode())
                .message(Objects.requireNonNull(fieldError).getDefaultMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongCredentialsException.class)
    public ErrorDTO handleException(WrongCredentialsException ex){
        log.error("{} error occurred while processing request.", ex.getMessage());
        return ErrorDTO.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .dateTime(ex.getDateTime())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConfirmationTokenInvalidException.class)
    public ErrorDTO handleException(ConfirmationTokenInvalidException ex){
        log.error ("{} error occurred while processing request.", ex.getMessage());
        return ErrorDTO.builder()
                .code(ExceptionEnum.CONFIRMATION_TOKEN_INVALID.getCode())
                .message(ex.getMessage())
                .dateTime(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ErrorDTO handleException(Throwable ex){
        log.error ("{} error occurred while processing request.", ex.getMessage());
        return ErrorDTO.builder()
                .code(ExceptionEnum.SERVER_ERROR.getCode())
                .message(ex.getMessage())
                .dateTime(LocalDateTime.now())
                .build();
    }

}
