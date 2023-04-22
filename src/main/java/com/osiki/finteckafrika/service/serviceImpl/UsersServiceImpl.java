package com.osiki.finteckafrika.service.serviceImpl;

import com.osiki.finteckafrika.model.UserRegistrationRequestModel;
import com.osiki.finteckafrika.repository.UsersRepository;
import com.osiki.finteckafrika.service.UsersService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
@Builder
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Override
    public String registerUser(UserRegistrationRequestModel registrationRequestModel) throws JSONException {
        return null;
    }
}
