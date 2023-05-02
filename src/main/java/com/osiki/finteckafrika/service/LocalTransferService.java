package com.osiki.finteckafrika.service;

import com.osiki.finteckafrika.model.LocalBankTransferModel;

public interface LocalTransferService {
    String localTransfer(final LocalBankTransferModel localBankTransferModel);
}
