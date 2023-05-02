package com.osiki.finteckafrika.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocalBankTransferModel {

    @Column(length = 10, nullable = false)
    private String accountNumber;
    @NotNull(message = "Bank name is required")
    private String bankName;
    @NotNull(message = "Amount is required")
    private BigDecimal amount;
    @NotNull(message = "Narration message is required")
    private String narration;
    @NotNull(message = "Pin is required")
    private String pin;
    @CreationTimestamp
    private LocalDateTime transactionDate;

}
