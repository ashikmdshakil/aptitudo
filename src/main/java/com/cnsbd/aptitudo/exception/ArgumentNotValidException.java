package com.cnsbd.aptitudo.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@Getter
@Setter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ArgumentNotValidException extends RuntimeException {
    private static final Long serialVersionUID = 1L;

    @JsonProperty("o_status_code")
    private Integer statusCode;

    @JsonProperty("o_status_message")
    private String message;

    @JsonProperty("error_details")
    private String errorDetails;

    @Override
    public String getMessage() {
        return message;
    }


    public ArgumentNotValidException(String message, String errorDetails) {
        super(message);
        this.message = message;
        this.statusCode = 0;
        this.errorDetails = errorDetails;
    }

    public ArgumentNotValidException(String message, Integer statusCode) {
        super(message);
        this.message = message;
        this.statusCode = statusCode;
        this.errorDetails = null;
    }

    public ArgumentNotValidException(String message, Integer statusCode, String errorDetails) {
        super(message);
        this.message = message;
        this.errorDetails = errorDetails;
        this.statusCode = statusCode;
    }

}
