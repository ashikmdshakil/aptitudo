package com.cnsbd.aptitudo.data.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmailRequestData {
    @JsonProperty("email_address")
    private String subscriberEmailAddress;
}
