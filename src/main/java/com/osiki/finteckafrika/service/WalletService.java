package com.osiki.finteckafrika.service;

import com.osiki.finteckafrika.exception.UserNotFoundException;
import com.osiki.finteckafrika.model.WalletModel;

public interface WalletService {
    WalletModel viewWalletDetails() throws UserNotFoundException;
}
