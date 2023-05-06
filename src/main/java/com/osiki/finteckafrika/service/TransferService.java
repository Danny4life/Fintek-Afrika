package com.osiki.finteckafrika.service;

import com.osiki.finteckafrika.entity.FlwBank;
import com.osiki.finteckafrika.entity.Transaction;
import com.osiki.finteckafrika.entity.Users;
import com.osiki.finteckafrika.request.ExternalBankTransferRequest;
import com.osiki.finteckafrika.request.FlwResolveAccountRequest;
import com.osiki.finteckafrika.response.FlwOtherBankTransferResponse;
import com.osiki.finteckafrika.response.FlwResolveAccountDetails;

import java.math.BigDecimal;
import java.util.List;

public interface TransferService {
    List<FlwBank> getAllBanks();

    FlwResolveAccountDetails resolveAccount(FlwResolveAccountRequest resolveAccountRequest);

    FlwOtherBankTransferResponse initiateOtherBankTransfer(ExternalBankTransferRequest bankTransferRequest);

    boolean validatePin(String pin, Users users);

    boolean validateWalletBalance(BigDecimal amount, Users users);

    boolean validateRequestBalance(BigDecimal amount);

    FlwOtherBankTransferResponse otherBankTransfer(ExternalBankTransferRequest bankTransferRequest, String clientRef);

    Transaction saveTransaction(Users users, ExternalBankTransferRequest bankTransferRequest);

    Users retrieveUserDetails();
}
