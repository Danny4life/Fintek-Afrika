package com.osiki.finteckafrika.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForgetPasswordRequest {
    @Email
    private String email;
}
