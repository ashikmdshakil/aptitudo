package com.cnsbd.aptitudo.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ProcedureException extends RuntimeException {
    private static final Long serialVersionUID = 1L;

    @JsonProperty("o_status_message")
    private String message;

    @JsonProperty("o_status_code")
    private Integer statusCode;

    @JsonProperty("error_details")
    private String errorDetails;

    public ProcedureException(String message) {
        super(message);
        this.statusCode = 99;
        this.message = message;
        this.errorDetails = null;
    }

    public ProcedureException(String message, String errorDetails) {
        super(message);
        this.statusCode = 99;
        this.message = message;
        this.errorDetails = errorDetails;
    }

    public ProcedureException(Integer statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
        this.errorDetails = null;
    }

    public ProcedureException(Integer statusCode, String message, String errorDetails) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
        this.errorDetails = errorDetails;
    }

}
