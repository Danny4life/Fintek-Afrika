package com.osiki.finteckafrika.service.serviceImpl;

import com.osiki.finteckafrika.model.UserRegistrationRequestModel;
import com.osiki.finteckafrika.repository.UsersRepository;
import com.osiki.finteckafrika.service.RegistrationService;
import com.osiki.finteckafrika.validations.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UsersRepository usersRepository;
    private final UsersServiceImpl usersService;
    private final EmailValidator emailValidator;
    private final MailServiceImpl mailService;


    @Override
    public String createUser(UserRegistrationRequestModel registrationRequestModel) throws JSONException {
        return null;
    }
}
