package com.osiki.finteckafrika.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wallet_tbl")
@Builder
public class Wallet extends BaseClass {
    private Double balance = 0.00;
    @Column(nullable = false)
    private String bankName;
    @Column(length = 10, nullable = false)
    @Size(min = 10, max = 10)
    private String accountNumber;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users users;

}
