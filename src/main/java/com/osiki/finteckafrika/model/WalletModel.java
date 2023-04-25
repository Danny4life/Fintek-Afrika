package com.osiki.finteckafrika.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletModel {

    private String firstName;
    private String lastName;
    private Double balance;
    private String accountNumber;
    private String bankName;

}
