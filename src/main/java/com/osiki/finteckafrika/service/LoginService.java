package com.osiki.finteckafrika.service;

import com.osiki.finteckafrika.model.LoginRequestPayload;

public interface LoginService {
    String authenticate(LoginRequestPayload loginRequestPayload) throws Exception;
}
