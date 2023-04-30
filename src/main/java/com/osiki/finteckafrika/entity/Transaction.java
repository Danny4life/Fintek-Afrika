package com.osiki.finteckafrika.entity;

import com.osiki.finteckafrika.enums.TransactionStatus;
import com.osiki.finteckafrika.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction_table")
@Builder
public class Transaction extends BaseClass{

    private String sourceAccountNumber;
    private String sourceBank;
    @Column(length = 10, nullable = false)
    private String destinationAccountNumber;
    private String destinationAccountName;
    @Column(nullable = false)
    private String destinationBank;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @CreationTimestamp
    private LocalDateTime transactionDate;
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
    @Column(nullable = false)
    private BigDecimal amount;
    private String narration;
    private String clientRef;
    private String flwRef;
    @ManyToOne
    @JoinColumn(name = "wallet_id", referencedColumnName = "id")
    private Wallet wallet;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users users;

}
