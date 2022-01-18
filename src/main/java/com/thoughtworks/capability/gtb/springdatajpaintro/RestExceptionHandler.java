package com.thoughtworks.capability.gtb.springdatajpaintro;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResult handleNotExistedUser(UserNotExistedException ex) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return new ErrorResult(
                httpStatus.value(),
                httpStatus.getReasonPhrase(),
                ex.getMessage()
        );
    }

}
