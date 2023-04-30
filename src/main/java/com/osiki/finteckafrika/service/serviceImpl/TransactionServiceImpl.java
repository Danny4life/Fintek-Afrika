package com.osiki.finteckafrika.service.serviceImpl;

import com.osiki.finteckafrika.repository.TransactionRepository;
import com.osiki.finteckafrika.repository.UsersRepository;
import com.osiki.finteckafrika.repository.WalletRepository;
import com.osiki.finteckafrika.service.TransactionService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Builder
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private UsersRepository usersRepository;
    private WalletRepository walletRepository;
    private PasswordEncoder passwordEncoder;
}
