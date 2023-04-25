package com.osiki.finteckafrika.service.serviceImpl;

import com.osiki.finteckafrika.model.WalletModel;
import com.osiki.finteckafrika.repository.UsersRepository;
import com.osiki.finteckafrika.repository.WalletRepository;
import com.osiki.finteckafrika.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final UsersRepository usersRepository;

    @Override
    public WalletModel viewWalletDetails() {
        return null;
    }
}
