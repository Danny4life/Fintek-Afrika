package com.osiki.finteckafrika.controller;


import com.osiki.finteckafrika.model.TransactionHistoryModel;
import com.osiki.finteckafrika.pagination_criteria.TransactionHistoryPages;
import com.osiki.finteckafrika.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/transaction_history")

    public ResponseEntity<PageImpl<TransactionHistoryModel>> viewTransactionHistory(TransactionHistoryPages transactionHistoryPages){
        return ResponseEntity.ok(transactionService.allTransaction(transactionHistoryPages));

    }
}
