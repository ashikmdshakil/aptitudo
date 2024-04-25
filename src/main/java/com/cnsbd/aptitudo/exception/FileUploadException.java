package com.cnsbd.aptitudo.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class FileUploadException extends RuntimeException {
    private static final Long serialVersionUID = 1L;

    @JsonProperty("o_status_message")
    private String message;

    @JsonProperty("o_status_code")
    private Integer statusCode;

    @JsonProperty("error_details")
    private String errorDetails;

    public FileUploadException(String message) {
        super(message);
        this.message = message;
        this.errorDetails = "";
        this.statusCode = 99;
    }
    public FileUploadException(String message, String errorDetails) {
        super(message);
        this.message = message;
        this.statusCode = 99;
        this.errorDetails = errorDetails;
    }


    public FileUploadException(String message, Integer statusCode, String errorDetails) {
        super(message);
        this.message = message;
        this.errorDetails = errorDetails;
        this.statusCode = statusCode;
    }

}
