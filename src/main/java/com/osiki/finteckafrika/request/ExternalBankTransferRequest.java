package com.osiki.finteckafrika.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalBankTransferRequest {

    @NotNull(message = "Account Number is required")
    private String accountNumber;
    @NotNull(message = "Bank code is required")
    private String bankCode;
    @NotNull(message = "Amount is required")
    private BigDecimal amount;
    private String narration;
    @NotNull(message = "Pin is required")
    private String pin;
}
