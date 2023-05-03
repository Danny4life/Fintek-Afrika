package com.osiki.finteckafrika.service.serviceImpl;

import com.osiki.finteckafrika.repository.TransactionRepository;
import com.osiki.finteckafrika.repository.UsersRepository;
import com.osiki.finteckafrika.repository.WalletRepository;
import com.osiki.finteckafrika.service.TransferService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Builder
public class TransferServiceImpl implements TransferService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
}
