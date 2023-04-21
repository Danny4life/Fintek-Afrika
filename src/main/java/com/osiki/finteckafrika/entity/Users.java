package com.osiki.finteckafrika.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_tbl")
public class Users extends BaseClass {

    private String firstName;
    private String lastName;
    private String email;
    private String bvn;
    private String phoneNumber;
    private String password;
    private String transactionPin;
}
