package com.osiki.finteckafrika.service.serviceImpl;

import com.osiki.finteckafrika.entity.Transaction;
import com.osiki.finteckafrika.entity.Users;
import com.osiki.finteckafrika.entity.Wallet;
import com.osiki.finteckafrika.exception.UserNotFoundException;
import com.osiki.finteckafrika.model.TransactionHistoryModel;
import com.osiki.finteckafrika.pagination_criteria.TransactionHistoryPages;
import com.osiki.finteckafrika.repository.TransactionRepository;
import com.osiki.finteckafrika.repository.UsersRepository;
import com.osiki.finteckafrika.repository.WalletRepository;
import com.osiki.finteckafrika.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private UsersRepository usersRepository;
    private WalletRepository walletRepository;

    @Override
    public PageImpl<TransactionHistoryModel> allTransaction(TransactionHistoryPages transactionHistoryPages) {

        String userEmail =  SecurityContextHolder.getContext().getAuthentication().getName();
        Users users = usersRepository.findUsersByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Sort sort = Sort.by(transactionHistoryPages.getSortDirection(), transactionHistoryPages.getSortBy());
        Pageable pageable = PageRequest.of(transactionHistoryPages.getPageNumber(), transactionHistoryPages.getPageSize(), sort);

        Wallet wallet = walletRepository.findWalletByUsers(users);
        List<Transaction> allTransactions = transactionRepository.findAllByWallet(wallet);
        String userAccountNumber = wallet.getAccountNumber();
        Page<Transaction> transactions = transactionRepository
                .findAllBySourceAccountNumberOrDestinationAccountNumber(userAccountNumber, userAccountNumber, pageable);

        List<TransactionHistoryModel> response = new ArrayList<>();

        for(Transaction transaction : allTransactions){
            TransactionHistoryModel transactionHistoryModel = TransactionHistoryModel.builder()
                    .id(transaction.getId())
                    .name(transaction.getDestinationAccountName())
                    .bank(transaction.getDestinationBank())
                    .transactionTime(transaction.getCreatedAt())
                    .transactionType(transaction.getTransactionType())
                    .amount(transaction.getAmount())
                    .build();

            response.add(transactionHistoryModel);
        }

        PageImpl<TransactionHistoryModel> transactionHistoryPage = new PageImpl<>(response, pageable, transactions.getTotalElements());

        return transactionHistoryPage;
    }
}
