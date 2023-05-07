package com.osiki.finteckafrika.controller;

import com.osiki.finteckafrika.model.LocalBankTransferModel;
import com.osiki.finteckafrika.service.serviceImpl.LocalTransferServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transfer")
public class LocalTransferController {

    private final LocalTransferServiceImpl localTransferService;


    @PutMapping("/localTransfer")
    public String localTransfer(@RequestBody LocalBankTransferModel localBankTransferModel){
        return localTransferService.localTransfer(localBankTransferModel);

    }
}
