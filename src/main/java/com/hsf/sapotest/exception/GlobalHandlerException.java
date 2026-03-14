package com.hsf.sapotest.exception;

import com.hsf.sapotest.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(value = {InvalidDataException.class, Exception.class})
    public ErrorResponse handleException(Exception e, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setError(HttpStatus.CONFLICT.getReasonPhrase());
        errorResponse.setStatus(HttpStatus.CONFLICT.value());
        errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
        errorResponse.setTimestamp(new Date());

        return errorResponse;
    }
}
