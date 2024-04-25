package com.cnsbd.cpapmis.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UnauthorizedUserException extends RuntimeException {

    private static final Long serialVersionUID = 1L;

    @JsonProperty("o_status_message")
    private String message;

    @JsonProperty("o_status_code")
    private Integer statusCode;


    public UnauthorizedUserException(String message) {
        super(message);
        this.message = message;
        this.statusCode = 99;
    }

    public UnauthorizedUserException(String message, Integer statusCode) {
        super(message);
        this.message = message;
        this.statusCode = statusCode;
    }
}
