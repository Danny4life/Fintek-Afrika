package com.osiki.finteckafrika.controller;

import com.osiki.finteckafrika.entity.FlwBank;
import com.osiki.finteckafrika.request.ExternalBankTransferRequest;
import com.osiki.finteckafrika.request.FlwResolveAccountRequest;
import com.osiki.finteckafrika.response.FlwOtherBankTransferResponse;
import com.osiki.finteckafrika.response.FlwResolveAccountDetails;
import com.osiki.finteckafrika.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transfer")
@RequiredArgsConstructor
public class OtherBankTransferController {

    private final TransferService transferService;

    @GetMapping("/banks")

    public List<FlwBank> getBanks(){

        return transferService.getAllBanks();
    }

    @PostMapping("/other-bank-account-query")

    public ResponseEntity<FlwResolveAccountDetails> resolvedAccountDetails(@RequestBody FlwResolveAccountRequest resolveAccountRequest){

        return new ResponseEntity<>(transferService.resolveAccount(resolveAccountRequest), HttpStatus.OK);
    }

    @PostMapping("/other-bank")
    public ResponseEntity<FlwOtherBankTransferResponse> processTransfer(@RequestBody ExternalBankTransferRequest bankTransferRequest){

        return new ResponseEntity<>(transferService.initiateOtherBankTransfer(bankTransferRequest), HttpStatus.OK);

    }
}
