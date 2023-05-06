package com.osiki.finteckafrika.response;

import com.osiki.finteckafrika.model.TransactionHistoryModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewTransactionHistoryResponse {

    private Page<TransactionHistoryModel> transactions;
}
