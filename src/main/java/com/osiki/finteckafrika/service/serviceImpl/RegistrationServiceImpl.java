package com.osiki.finteckafrika.service.serviceImpl;

import com.osiki.finteckafrika.entity.Users;
import com.osiki.finteckafrika.exception.ConfirmationTokenException;
import com.osiki.finteckafrika.exception.EmailNotValidException;
import com.osiki.finteckafrika.exception.TokenNotFoundException;
import com.osiki.finteckafrika.exception.UserNotFoundException;
import com.osiki.finteckafrika.model.MailServiceModel;
import com.osiki.finteckafrika.model.UserRegistrationRequestModel;
import com.osiki.finteckafrika.repository.UsersRepository;
import com.osiki.finteckafrika.service.RegistrationService;
import com.osiki.finteckafrika.token.ConfirmationToken;
import com.osiki.finteckafrika.util.Constant;
import com.osiki.finteckafrika.validations.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UsersRepository usersRepository;
    private final UsersServiceImpl usersService;
    private final EmailValidator emailValidator;
    private final MailServiceImpl mailService;
    private final ConfirmationTokenServiceImpl confirmationTokenService;


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
    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token)
                .orElseThrow(() -> new TokenNotFoundException("Token Not Found"));

        if(confirmationToken.getConfirmedAt() != null){
            throw new ConfirmationTokenException("Email Already Confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if(expiredAt.isBefore(LocalDateTime.now())){
            Users users = usersRepository.findByEmail(confirmationToken.getUsers().getEmail())
                    .orElseThrow(() -> new UserNotFoundException("User Not Found"));

            resendEmail(users);
            return "Verification token expired. Check your email for a new verification token";
        }
        confirmationTokenService.setConfirmedAt(token);
        usersService.enableUser(
                confirmationToken.getUsers().getEmail());

        return "Confirmed";

    }

    @Override
    public void resendEmail(Users users) {
        String token = UUID.randomUUID().toString();
        String link = Constant.EMAIL_VERIFICATION_TOKEN_LINK + token;
        sendMail(users.getFirstName(), users.getEmail(), link);

        usersService.saveToken(token, users);
    }


    @Override
    public void sendMail(String name, String email, String link) {

        String subject = "Email Verification";
        String body = "Click the link to verify your profile \n" + link;
        MailServiceModel mailServiceModel = new MailServiceModel(name, email, body, subject);
        mailService.sendNotification(mailServiceModel);

    }


}
