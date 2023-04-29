package com.osiki.finteckafrika.service;

import com.osiki.finteckafrika.model.LoginRequestPayload;
import com.osiki.finteckafrika.request.ForgetPasswordRequest;
import com.osiki.finteckafrika.request.PasswordRequest;

public interface LoginService {
    String authenticate(LoginRequestPayload loginRequestPayload) throws Exception;

    String generateResetToken(ForgetPasswordRequest forgetPasswordRequest);

    String resetPassword(PasswordRequest passwordRequest, String token);
}
