package com.osiki.finteckafrika.service.serviceImpl;

import com.osiki.finteckafrika.entity.FlwBank;
import com.osiki.finteckafrika.entity.Transaction;
import com.osiki.finteckafrika.entity.Users;
import com.osiki.finteckafrika.entity.Wallet;
import com.osiki.finteckafrika.exception.ErrorException;
import com.osiki.finteckafrika.exception.IncorrectDetailsException;
import com.osiki.finteckafrika.exception.UserNotFoundException;
import com.osiki.finteckafrika.repository.TransactionRepository;
import com.osiki.finteckafrika.repository.UsersRepository;
import com.osiki.finteckafrika.repository.WalletRepository;
import com.osiki.finteckafrika.request.ExternalBankTransferRequest;
import com.osiki.finteckafrika.request.FlwResolveAccountRequest;
import com.osiki.finteckafrika.response.FlwGetAllBankResponse;
import com.osiki.finteckafrika.response.FlwOtherBankTransferResponse;
import com.osiki.finteckafrika.response.FlwResolveAccountDetails;
import com.osiki.finteckafrika.service.TransferService;
import com.osiki.finteckafrika.util.Constant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Builder
public class TransferServiceImpl implements TransferService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public List<FlwBank> getAllBanks() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.add("Authorization", "Bearer" + Constant.AUTHORIZATION);

        HttpEntity<FlwGetAllBankResponse> httpEntity = new HttpEntity<>(null, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();

        FlwGetAllBankResponse flwGetAllBankResponse = restTemplate.exchange(
                Constant.GET_ALL_BANKS,
                HttpMethod.GET,
                httpEntity,
                FlwGetAllBankResponse.class
        ).getBody();

        return flwGetAllBankResponse.getData();
    }

    @Override
    public FlwResolveAccountDetails resolveAccount(FlwResolveAccountRequest resolveAccountRequest) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.add("Authorization", "Bearer" + Constant.AUTHORIZATION);

        HttpEntity<FlwResolveAccountRequest> accountRequestHttpEntity = new HttpEntity<>(resolveAccountRequest, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();

        FlwResolveAccountDetails resolveAccountDetails = restTemplate.exchange(
                Constant.RESOLVE_ACCOUNT_DETAILS,
                HttpMethod.POST,
                accountRequestHttpEntity,
                FlwResolveAccountDetails.class
        ).getBody();

        return resolveAccountDetails;
    }

    @Override
    public FlwOtherBankTransferResponse initiateOtherBankTransfer(ExternalBankTransferRequest bankTransferRequest) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users users = usersRepository.findByEmail(user.getUsername())
                .orElseThrow(()-> new UserNotFoundException("User does not exist"));

        if(!validatePin(bankTransferRequest.getPin(), users)){
            throw new IncorrectDetailsException("Incorrect Pin");
        }

        if(!validateRequestBalance(bankTransferRequest.getAmount())){
            throw new IncorrectDetailsException("Transfer must be above zero naira");
        }

        if(!validateWalletBalance(bankTransferRequest.getAmount(), users)){
            throw new IncorrectDetailsException("Insufficient Balance");
        }

        String clientRef = UUID.randomUUID().toString();
        
        FlwOtherBankTransferResponse flwOtherBankTransferResponse = otherBankTransfer(bankTransferRequest, clientRef);

        if(!flwOtherBankTransferResponse.getStatus().equalsIgnoreCase("success")){
            throw new ErrorException("An Error Occurred");
        }

        Transaction transaction = saveTransaction(users, bankTransferRequest);
        transaction.setClientRef(clientRef);
        transaction.setFlwRef(flwOtherBankTransferResponse.getData().toString());
        transactionRepository.save(transaction);

        return flwOtherBankTransferResponse;
    }

    @Override
    public Users retrieveUserDetails() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users users = usersRepository.findByEmail(user.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User NotFound"));
        return users;
    }

    public boolean validatePin(String pin, Users users) {
        return passwordEncoder.matches(pin, users.getTransactionPin());
    }

    public boolean validateRequestBalance(BigDecimal amount) {
        int request = amount.compareTo(BigDecimal.valueOf(0));

        return request > 0;
    }

    public boolean validateWalletBalance(BigDecimal amount, Users users) {
        Wallet wallet = walletRepository.findWalletByUsers(users);
        int result = wallet.getBalance().compareTo(amount.doubleValue());

        if(result == 0 || result == 1) return true;
        return false;
    }

    public Transaction saveTransaction(Users users, ExternalBankTransferRequest bankTransferRequest) {

    }



    public FlwOtherBankTransferResponse otherBankTransfer(ExternalBankTransferRequest bankTransferRequest, String clientRef) {

    }






}
