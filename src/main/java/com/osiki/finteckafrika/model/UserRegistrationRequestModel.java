package com.osiki.finteckafrika.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegistrationRequestModel {

    private String firstName;
    private String lastName;
    private String email;
    private String bvn;
    private String phoneNumber;
    private String password;
    private String confirmPassword;
    private String transactionPin;
}
