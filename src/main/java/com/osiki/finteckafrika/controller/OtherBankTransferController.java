package com.osiki.finteckafrika.controller;

import com.osiki.finteckafrika.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transfer")
@RequiredArgsConstructor
public class OtherBankTransferController {

    private final TransferService transferService;
}
