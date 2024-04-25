package com.cnsbd.aptitudo.exception.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ErrorDetails {
//    @JsonProperty("status")
//    private boolean status;

    @JsonProperty("o_status_code") // o_STATUS_CODE
    private Integer statusCode;

    @JsonProperty("o_status_message")
    private String message;

    @JsonProperty("error_details")
    private String errorDetails;

    public ErrorDetails(String message) {
        super();
//        this.status = false;
        this.statusCode = 99;
        this.message = message;
    }
    public ErrorDetails(String message, String errorDetails) {
        super();
//        this.status = false;
        this.statusCode = 99;
        this.message = message;
        this.errorDetails = errorDetails;
    }

    public ErrorDetails(String message, Integer statusCode) {
        super();
//        this.status = false;
        this.statusCode = statusCode;
        this.message = message;
    }

    public ErrorDetails(String message, Integer statusCode, String errorDetails) {
        super();
//        this.status = false;
        this.statusCode = statusCode;
        this.message = message;
        this.errorDetails = errorDetails;
    }

}
