package com.osiki.finteckafrika.service.serviceImpl;

import com.osiki.finteckafrika.configuration.PasswordEncoder;
import com.osiki.finteckafrika.model.UserRegistrationRequestModel;
import com.osiki.finteckafrika.repository.UsersRepository;
import com.osiki.finteckafrika.service.UsersService;
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

    @Override
    public String registerUser(UserRegistrationRequestModel registrationRequestModel) throws JSONException {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
