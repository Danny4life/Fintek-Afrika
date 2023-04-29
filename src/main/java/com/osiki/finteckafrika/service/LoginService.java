package com.osiki.finteckafrika.service;

import com.osiki.finteckafrika.model.LoginRequestPayload;
import com.osiki.finteckafrika.request.ForgetPasswordRequest;

public interface LoginService {
    String authenticate(LoginRequestPayload loginRequestPayload) throws Exception;

    String generateResetToken(ForgetPasswordRequest forgetPasswordRequest);
}
