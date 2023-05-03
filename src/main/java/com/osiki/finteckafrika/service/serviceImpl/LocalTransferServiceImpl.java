package com.osiki.finteckafrika.service.serviceImpl;


import com.osiki.finteckafrika.entity.Transaction;
import com.osiki.finteckafrika.entity.Users;
import com.osiki.finteckafrika.entity.Wallet;
import com.osiki.finteckafrika.enums.TransactionStatus;
import com.osiki.finteckafrika.enums.TransactionType;
import com.osiki.finteckafrika.exception.IncorrectTransactionPinException;
import com.osiki.finteckafrika.exception.InsufficientBalanceException;
import com.osiki.finteckafrika.exception.InvalidAmountException;
import com.osiki.finteckafrika.exception.WalletNotFoundException;
import com.osiki.finteckafrika.model.LocalBankTransferModel;
import com.osiki.finteckafrika.repository.TransactionRepository;
import com.osiki.finteckafrika.repository.UsersRepository;
import com.osiki.finteckafrika.repository.WalletRepository;
import com.osiki.finteckafrika.service.LocalTransferService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Builder
public class LocalTransferServiceImpl implements LocalTransferService {

    private final WalletRepository walletRepository;
    private final UsersRepository usersRepository;
    private final TransactionRepository transactionRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String localTransfer(LocalBankTransferModel localBankTransferModel) {
        UUID uuid = UUID.randomUUID();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = user.getUsername();
        Users loggedInUser = usersRepository.findUsersByEmail(email).get();
        Wallet receiverWallet = walletRepository.findWalletByAccountNumberAndBankName(localBankTransferModel.getAccountNumber(),
                localBankTransferModel.getBankName()).orElse(null);
        Wallet senderWallet = walletRepository.findUsersWalletById(loggedInUser.getId());
        Users receiverUser = receiverWallet.getUsers();

        if(localBankTransferModel.getAmount().doubleValue() <= 0){
            throw new InvalidAmountException("Amount transfer is invalid");
        }

        if(receiverWallet == null){
            throw new WalletNotFoundException("Invalid account name or number");
        }

        if(senderWallet.getBalance() < localBankTransferModel.getAmount().doubleValue()){
            throw new InsufficientBalanceException("Insufficient Balance");
        }

        if(!passwordEncoder.matches(localBankTransferModel.getPin(), loggedInUser.getTransactionPin())){
            throw new IncorrectTransactionPinException("Incorrect Transaction PIN");
        }

        Transaction sendTransaction = new Transaction();
        Transaction receiverTransaction = new Transaction();

        try{
            double senderBalance = walletRepository.findUsersWalletById(usersRepository.findByEmail(user.getUsername()).get()
                    .getId()).getBalance();

            double receiverBalance = walletRepository.findWalletByAccountNumber(receiverWallet.getAccountNumber())
                    .getBalance();

            receiverWallet.setBalance(receiverBalance+localBankTransferModel.getAmount().doubleValue());
            senderWallet.setBalance(senderBalance - localBankTransferModel.getAmount().doubleValue());

            walletRepository.save(receiverWallet);
            walletRepository.save(senderWallet);

            sendTransaction.setTransactionStatus(TransactionStatus.SUCCESS);

        }catch (Exception e){
            sendTransaction.setTransactionStatus(TransactionStatus.FAILED);
            throw new RuntimeException(e);
        }

        sendTransaction = Transaction.builder()
                .amount(localBankTransferModel.getAmount())
                .destinationAccountNumber(receiverWallet.getBankName())
                .narration(localBankTransferModel.getNarration())
                .destinationBank(receiverWallet.getBankName())
                .destinationAccountNumber(localBankTransferModel.getAccountNumber())
                .sourceAccountNumber(senderWallet.getAccountNumber())
                .transactionType(TransactionType.DEBIT)
                .transactionDate(localBankTransferModel.getTransactionDate())
                .sourceBank(senderWallet.getBankName())
                .clientRef(uuid.toString())
                .users(loggedInUser)
                .wallet(senderWallet)
                .build();

        transactionRepository.save(sendTransaction);


        return null;
    }
}
