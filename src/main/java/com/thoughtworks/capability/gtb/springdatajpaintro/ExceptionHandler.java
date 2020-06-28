package com.thoughtworks.capability.gtb.springdatajpaintro;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(UserNotExistedException.class)
    public ResponseEntity<ApiError> handleNotExistedUser(UserNotExistedException ex, WebRequest webRequest) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(new ApiError(httpStatus, ex.getMessage()), httpStatus);
    }

}
