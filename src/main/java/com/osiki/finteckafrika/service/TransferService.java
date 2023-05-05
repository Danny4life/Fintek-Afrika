package com.osiki.finteckafrika.service;

import com.osiki.finteckafrika.entity.FlwBank;
import com.osiki.finteckafrika.request.ExternalBankTransferRequest;
import com.osiki.finteckafrika.request.FlwResolveAccountRequest;
import com.osiki.finteckafrika.response.FlwOtherBankTransferResponse;
import com.osiki.finteckafrika.response.FlwResolveAccountDetails;

import java.util.List;

public interface TransferService {
    List<FlwBank> getAllBanks();

    FlwResolveAccountDetails resolveAccount(FlwResolveAccountRequest resolveAccountRequest);

    FlwOtherBankTransferResponse initiateOtherBankTransfer(ExternalBankTransferRequest bankTransferRequest);
}
