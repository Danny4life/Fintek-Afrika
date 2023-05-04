package com.osiki.finteckafrika.service.serviceImpl;

import com.osiki.finteckafrika.entity.FlwBank;
import com.osiki.finteckafrika.repository.TransactionRepository;
import com.osiki.finteckafrika.repository.UsersRepository;
import com.osiki.finteckafrika.repository.WalletRepository;
import com.osiki.finteckafrika.response.FlwGetAllBankResponse;
import com.osiki.finteckafrika.response.FlwResolveAccountDetails;
import com.osiki.finteckafrika.service.TransferService;
import com.osiki.finteckafrika.util.Constant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

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
    public FlwResolveAccountDetails resolveAccount(FlwResolveAccountDetails accountDetails) {
        return null;
    }
}
