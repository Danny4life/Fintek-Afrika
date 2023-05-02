package com.osiki.finteckafrika.service.serviceImpl;

import com.osiki.finteckafrika.configuration.PasswordEncoder;
import com.osiki.finteckafrika.entity.Users;
import com.osiki.finteckafrika.entity.Wallet;
import com.osiki.finteckafrika.model.LocalBankTransferModel;
import com.osiki.finteckafrika.repository.TransactionRepository;
import com.osiki.finteckafrika.repository.UsersRepository;
import com.osiki.finteckafrika.repository.WalletRepository;
import com.osiki.finteckafrika.service.LocalTransferService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
        return null;
    }
}
