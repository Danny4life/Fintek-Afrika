package com.osiki.finteckafrika.service.serviceImpl;

import com.osiki.finteckafrika.entity.Users;
import com.osiki.finteckafrika.entity.Wallet;
import com.osiki.finteckafrika.exception.UserNotFoundException;
import com.osiki.finteckafrika.model.WalletModel;
import com.osiki.finteckafrika.repository.UsersRepository;
import com.osiki.finteckafrika.repository.WalletRepository;
import com.osiki.finteckafrika.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final UsersRepository usersRepository;

    @Override
    public WalletModel viewWalletDetails() throws UserNotFoundException {

        WalletModel walletModel = new WalletModel();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users users = usersRepository.findByEmail(user.getUsername()).
                orElseThrow(() -> new UserNotFoundException("Not found"));

        Wallet wallet = users.getWallet();
        walletModel.setBalance(wallet.getBalance());
        walletModel.setBankName(wallet.getBankName());
        walletModel.setAccountNumber(wallet.getAccountNumber());
        BeanUtils.copyProperties(users, walletModel);
        return walletModel;
    }
}
