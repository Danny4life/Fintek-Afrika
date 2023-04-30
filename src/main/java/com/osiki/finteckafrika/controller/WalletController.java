package com.osiki.finteckafrika.controller;

import com.osiki.finteckafrika.model.AccountFundModel;
import com.osiki.finteckafrika.model.WalletModel;
import com.osiki.finteckafrika.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @RequestMapping("/viewWallet")
    public ResponseEntity<WalletModel> viewWallet(){
        WalletModel walletModel = walletService.viewWalletDetails();

        return new ResponseEntity<>(walletModel, HttpStatus.OK);
    }

    @PostMapping("/fundWallet")

    public ResponseEntity<String> accountFund(@RequestBody AccountFundModel amount){
        return new ResponseEntity<>(walletService.fundWallet(amount), HttpStatus.OK);

    }
}
