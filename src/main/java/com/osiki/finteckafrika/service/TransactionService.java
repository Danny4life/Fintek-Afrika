package com.osiki.finteckafrika.service;

import com.osiki.finteckafrika.model.TransactionHistoryModel;
import com.osiki.finteckafrika.pagination_criteria.TransactionHistoryPages;
import org.springframework.data.domain.PageImpl;

public interface TransactionService {
    PageImpl<TransactionHistoryModel> allTransaction(TransactionHistoryPages transactionHistoryPages);
}
