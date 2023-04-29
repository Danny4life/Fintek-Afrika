package com.osiki.finteckafrika.service.serviceImpl;

import com.osiki.finteckafrika.configuration.PasswordEncoder;
import com.osiki.finteckafrika.exception.UserNotFoundException;
import com.osiki.finteckafrika.model.LoginRequestPayload;
import com.osiki.finteckafrika.repository.UsersRepository;
import com.osiki.finteckafrika.service.LoginService;
import com.osiki.finteckafrika.util.JwtUtil;
import com.osiki.finteckafrika.util.Util;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    private final UsersRepository usersRepository;
    private final MailServiceImpl mailService;
    private final Util util;
    private final PasswordEncoder passwordEncoder;
    @Override
    public String authenticate(LoginRequestPayload loginRequestPayload) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestPayload.getEmail(), loginRequestPayload.getPassword())
            );
        }catch (BadCredentialsException e){
            throw new UserNotFoundException("Invalid Credentials");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestPayload.getEmail());
        return jwtUtil.generateToken(userDetails);
    }
}
