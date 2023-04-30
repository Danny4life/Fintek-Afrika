package com.osiki.finteckafrika.entity;

import com.osiki.finteckafrika.enums.UsersStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_tbl")
public class Users extends BaseClass {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @Column(length = 11, unique = true, nullable = false)
    private String bvn;

    @Column(length = 11)
    @Size(max = 11, min = 11, message = "Phone number must be 11 digits")
    private String phoneNumber;

    @Column(nullable = false)
    @Size(min = 8, message = "Password should not be less than 8 characters")
    private String password;

    @Size(max = 6, min = 6, message = "Transaction pin must be 6 characters")
    private String transactionPin;

    @Enumerated(EnumType.STRING)
    private UsersStatus usersStatus = UsersStatus.INACTIVE;

    @OneToOne(mappedBy = "users")
    private Wallet wallet;

    @OneToMany(mappedBy = "users")
    private List<Transaction> transactions;

    private String role = "USER";
}
