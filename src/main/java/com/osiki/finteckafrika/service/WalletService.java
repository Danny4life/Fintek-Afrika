package com.osiki.finteckafrika.service;

import com.osiki.finteckafrika.entity.Wallet;
import com.osiki.finteckafrika.exception.UserNotFoundException;
import com.osiki.finteckafrika.model.WalletModel;
import com.osiki.finteckafrika.request.FlwWalletRequest;
import org.springframework.boot.configurationprocessor.json.JSONException;

public interface WalletService {
    WalletModel viewWalletDetails() throws UserNotFoundException;

    Wallet createWallet(FlwWalletRequest walletRequest) throws JSONException;
}
