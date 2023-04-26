package com.osiki.finteckafrika.service.serviceImpl;

import com.osiki.finteckafrika.entity.Users;
import com.osiki.finteckafrika.entity.Wallet;
import com.osiki.finteckafrika.enums.UsersStatus;
import com.osiki.finteckafrika.exception.EmailAlreadyTakenException;
import com.osiki.finteckafrika.exception.UserNotFoundException;
import com.osiki.finteckafrika.model.UserRegistrationRequestModel;
import com.osiki.finteckafrika.repository.ConfirmationTokenRepository;
import com.osiki.finteckafrika.repository.UsersRepository;
import com.osiki.finteckafrika.repository.WalletRepository;
import com.osiki.finteckafrika.request.FlwWalletRequest;
import com.osiki.finteckafrika.service.UsersService;
import com.osiki.finteckafrika.service.WalletService;
import com.osiki.finteckafrika.token.ConfirmationToken;
import com.osiki.finteckafrika.util.Util;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
@Builder
public class UsersServiceImpl implements UserDetailsService, UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final ConfirmationTokenServiceImpl tokenService;

    private final WalletService walletService;
    private final WalletRepository walletRepository;
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
            throw new EmailAlreadyTakenException("Email already taken");
        }
        if(!passwordMatch){
            throw new InputMismatchException("Password do not match!");
        }

       registrationRequestModel.setTransactionPin(passwordEncoder.
               encode(registrationRequestModel.getTransactionPin()));

        registrationRequestModel.setPassword(passwordEncoder
                .encode(registrationRequestModel.getPassword()));

        BeanUtils.copyProperties(registrationRequestModel, user);
        Users user1 = usersRepository.save(user);



        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(10),
                user
        );

        confirmationTokenRepository.save(confirmationToken);

        Wallet wallet = walletService.createWallet(FlwWalletRequest.builder()
                .firstname(user1.getFirstName())
                .lastname(user1.getLastName())
                .email(user1.getEmail())
                .phoneNumber(user1.getPhoneNumber())
                .bvn(user1.getBvn())
                .build());
        wallet.setUsers(user1);
        wallet.setBalance(0.00);
        walletRepository.save(wallet);

        return token;

    }

    @Override
    public void enableUser(String email) {
        Users users = usersRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));

        users.setUsersStatus(UsersStatus.ACTIVE);
        usersRepository.save(users);
    }

    @Override
    public void saveToken(String token, Users users) {
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(10),
                users
        );

        tokenService.saveConfirmationToken(confirmationToken);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }


}
