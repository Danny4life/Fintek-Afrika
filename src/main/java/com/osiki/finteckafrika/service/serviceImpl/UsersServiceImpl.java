package com.osiki.finteckafrika.service.serviceImpl;

import com.osiki.finteckafrika.configuration.PasswordEncoder;
import com.osiki.finteckafrika.entity.Users;
import com.osiki.finteckafrika.model.UserRegistrationRequestModel;
import com.osiki.finteckafrika.repository.ConfirmationTokenRepository;
import com.osiki.finteckafrika.repository.UsersRepository;
import com.osiki.finteckafrika.service.UsersService;
import com.osiki.finteckafrika.util.Util;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
@Builder
public class UsersServiceImpl implements UserDetailsService, UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final ConfirmationTokenServiceImpl tokenService;
    private final Util util;

    @Override
    public String registerUser(UserRegistrationRequestModel registrationRequestModel) throws JSONException {
        // create an object of User entity
        Users user = new Users();
        // create a boolean variable user exists to find if user email is present in the database
        boolean userExists = usersRepository.findByEmail(registrationRequestModel.getEmail()).isPresent();
        // create a boolean variable password match to check and confirm if password and confirm password match
        boolean passwordMatch = util.validatePassword(registrationRequestModel.getPassword(),
                registrationRequestModel.getConfirmPassword());

        if(userExists){

        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
