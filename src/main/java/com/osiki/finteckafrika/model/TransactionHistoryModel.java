package com.osiki.finteckafrika.model;

import com.osiki.finteckafrika.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionHistoryModel {

    private Long id;
    private String name;
    private String bank;
    private LocalDateTime transactionTime;
    private TransactionType transactionType;
    private BigDecimal amount;
}
