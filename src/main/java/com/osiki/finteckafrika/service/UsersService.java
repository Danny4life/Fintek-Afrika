package com.osiki.finteckafrika.service;

import com.osiki.finteckafrika.entity.Users;
import com.osiki.finteckafrika.model.UserRegistrationRequestModel;
import com.osiki.finteckafrika.response.UserResponse;
import org.springframework.boot.configurationprocessor.json.JSONException;

public interface UsersService {
    String registerUser(UserRegistrationRequestModel registrationRequestModel) throws JSONException;

    void enableUser(String email);

    void saveToken(String token, Users users);

    UserResponse getUser();
}
