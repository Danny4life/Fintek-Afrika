package com.osiki.finteckafrika.service;

import com.osiki.finteckafrika.entity.Wallet;
import com.osiki.finteckafrika.exception.UserNotFoundException;
import com.osiki.finteckafrika.model.AccountFundModel;
import com.osiki.finteckafrika.model.WalletModel;
import com.osiki.finteckafrika.request.FlwWalletRequest;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;

public interface WalletService {
    WalletModel viewWalletDetails() throws UserNotFoundException;

    Wallet createWallet(FlwWalletRequest walletRequest) throws JSONException;

    public ResponseEntity<String> fundWallet(AccountFundModel amount);
}
