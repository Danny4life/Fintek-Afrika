package com.osiki.finteckafrika.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlwOtherBankTransferRequest {

    @JsonProperty("account_bank")
    private String accountBank;
    @JsonProperty("account_number")
    private String accountNumber;
    private Double amount;
    private String narration;
    private String reference;
    private String currency;
    private String callBackUrl;
    private String debitCurrency;
}
