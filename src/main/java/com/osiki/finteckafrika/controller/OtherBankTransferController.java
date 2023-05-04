package com.osiki.finteckafrika.controller;

import com.osiki.finteckafrika.entity.FlwBank;
import com.osiki.finteckafrika.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
