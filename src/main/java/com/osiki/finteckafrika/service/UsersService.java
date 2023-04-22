package com.osiki.finteckafrika.service;

import com.osiki.finteckafrika.model.UserRegistrationRequestModel;
import org.springframework.boot.configurationprocessor.json.JSONException;

public interface UsersService {
    String registerUser(UserRegistrationRequestModel registrationRequestModel) throws JSONException;
}
