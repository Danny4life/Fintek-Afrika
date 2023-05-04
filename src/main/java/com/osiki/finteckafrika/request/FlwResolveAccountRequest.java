package com.osiki.finteckafrika.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlwResolveAccountRequest {

    @JsonProperty("account_number")
    private String accountNumber;
    @JsonProperty("account_bank")
    private String accountBank;
}
