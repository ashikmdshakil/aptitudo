package com.cnsbd.aptitudo.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcedureSuccessException extends RuntimeException {
    private static final Long serialVersionUID = 1L;

    @JsonProperty("o_status_message")
    private String message;

    @JsonProperty("o_status_code")
    private Integer statusCode;

    public ProcedureSuccessException(String message, Integer statusCode) {
        super(message);
        this.message = message;
        this.statusCode = statusCode;
    }

}
