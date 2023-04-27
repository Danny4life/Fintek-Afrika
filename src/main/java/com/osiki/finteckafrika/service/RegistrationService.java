package com.osiki.finteckafrika.service;

import com.osiki.finteckafrika.entity.Users;
import com.osiki.finteckafrika.model.UserRegistrationRequestModel;
import org.springframework.boot.configurationprocessor.json.JSONException;

public interface RegistrationService {
    String createUser(UserRegistrationRequestModel registrationRequestModel) throws JSONException;

    void sendMail(String name, String email, String link);

    String confirmToken(String token);

    void resendEmail(Users user);
}
