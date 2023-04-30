package com.osiki.finteckafrika.service.serviceImpl;

import com.osiki.finteckafrika.repository.TransactionRepository;
import com.osiki.finteckafrika.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
}
