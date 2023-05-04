package com.osiki.finteckafrika.service;

import com.osiki.finteckafrika.entity.FlwBank;
import com.osiki.finteckafrika.response.FlwResolveAccountDetails;

import java.util.List;

public interface TransferService {
    List<FlwBank> getAllBanks();

    FlwResolveAccountDetails resolveAccount(FlwResolveAccountDetails accountDetails);
}
