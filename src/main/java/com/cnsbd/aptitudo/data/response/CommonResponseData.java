package com.cnsbd.aptitudo.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CommonResponseData {
    @JsonProperty("o_status_code")
    private Integer oStatusCode;
    @JsonProperty("o_status_message")
    private String oStatusMessage;

    public CommonResponseData(String statusMessage) {
        this.oStatusCode = 1;
        this.oStatusMessage = statusMessage;
    }
}
