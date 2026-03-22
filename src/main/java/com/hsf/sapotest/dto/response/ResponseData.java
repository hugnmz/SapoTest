package com.hsf.sapotest.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
public class ResponseData<T> {

    private final Integer httpStatus;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public ResponseData(Integer httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public ResponseData(Integer httpStatus, String message, T data) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.data = data;
    }

    public ResponseData(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }


}
