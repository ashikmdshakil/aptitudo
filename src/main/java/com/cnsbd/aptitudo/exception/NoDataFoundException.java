package com.cnsbd.aptitudo.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoDataFoundException extends RuntimeException {
    private static final Long serialVersionUID = 1L;

    @JsonProperty("o_status_message")
    private String message;

    @JsonProperty("o_status_code")
    private Integer statusCode;

    @JsonProperty("error_details")
    private String errorDetails;

    public NoDataFoundException(String message) {
        super(message);
        this.message = message;
        this.errorDetails = "";
        this.statusCode = 99;
    }

    public NoDataFoundException(String s, int i) {
        super("NO DATA FOUND!");
        this.message = "NO DATA FOUND!";
        this.errorDetails = "";
        this.statusCode = 99;
    }

}
