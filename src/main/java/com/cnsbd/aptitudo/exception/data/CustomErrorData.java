package com.cnsbd.aptitudo.exception.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomErrorData {

    private static final Long serialVersionUID = 1L;

    @JsonProperty("o_status_message")
    private String message;

    @JsonProperty("o_status_code")
    private Integer statusCode;

    @JsonProperty("status")
    private final boolean status = false;

    public CustomErrorData(String message, Integer statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

}
