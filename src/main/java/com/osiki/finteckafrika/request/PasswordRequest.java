package com.osiki.finteckafrika.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordRequest {

    @Size(min = 4, message = "Password should not be less than four characters")
    private String newPassword;
    @Size(min = 4, message = "Password should not be less than four characters")
    private String confirmPassword;
}
