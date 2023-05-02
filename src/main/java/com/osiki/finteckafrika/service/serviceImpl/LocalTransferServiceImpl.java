package com.osiki.finteckafrika.service.serviceImpl;

import com.osiki.finteckafrika.configuration.PasswordEncoder;
import com.osiki.finteckafrika.repository.TransactionRepository;
import com.osiki.finteckafrika.repository.UsersRepository;
import com.osiki.finteckafrika.repository.WalletRepository;
import com.osiki.finteckafrika.service.LocalTransferService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Builder
public class LocalTransferServiceImpl implements LocalTransferService {

    private final WalletRepository walletRepository;
    private final UsersRepository usersRepository;
    private final TransactionRepository transactionRepository;
    private final PasswordEncoder passwordEncoder;
}
