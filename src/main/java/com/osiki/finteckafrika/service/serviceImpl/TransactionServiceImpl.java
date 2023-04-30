package com.osiki.finteckafrika.service.serviceImpl;

import com.osiki.finteckafrika.model.TransactionHistoryModel;
import com.osiki.finteckafrika.pagination_criteria.TransactionHistoryPages;
import com.osiki.finteckafrika.repository.TransactionRepository;
import com.osiki.finteckafrika.repository.UsersRepository;
import com.osiki.finteckafrika.repository.WalletRepository;
import com.osiki.finteckafrika.service.TransactionService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
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

    @Override
    public PageImpl<TransactionHistoryModel> allTransaction(TransactionHistoryPages transactionHistoryPages) {
        return null;
    }
}
