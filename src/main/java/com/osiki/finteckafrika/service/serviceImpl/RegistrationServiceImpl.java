package com.osiki.finteckafrika.service.serviceImpl;

import com.osiki.finteckafrika.exception.EmailNotValidException;
import com.osiki.finteckafrika.model.MailServiceModel;
import com.osiki.finteckafrika.model.UserRegistrationRequestModel;
import com.osiki.finteckafrika.repository.UsersRepository;
import com.osiki.finteckafrika.service.RegistrationService;
import com.osiki.finteckafrika.util.Constant;
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
        boolean isValidEmail = emailValidator.test(registrationRequestModel.getEmail());
        if(!isValidEmail){
            throw new EmailNotValidException("Email is not valid");
        }

        String token = usersService.registerUser(registrationRequestModel);

        String link = Constant.EMAIL_VERIFICATION_TOKEN_LINK + token;
        sendMail(registrationRequestModel.getFirstName(),
                registrationRequestModel.getEmail(), link);

        return "Please check your email to verify your account";
    }

    @Override
    public void sendMail(String name, String email, String link) {

        String subject = "Email Verification";
        String body = "Click the link to verify your profile \n" + link;
        MailServiceModel mailServiceModel = new MailServiceModel(name, email, body, subject);
        mailService.sendNotification(mailServiceModel);

    }

    @Override
    public String confirmToken(String token) {
        return null;
    }

}
