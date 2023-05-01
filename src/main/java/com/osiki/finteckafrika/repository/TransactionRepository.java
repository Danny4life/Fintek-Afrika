package com.osiki.finteckafrika.repository;

import com.osiki.finteckafrika.entity.Transaction;
import com.osiki.finteckafrika.entity.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> finaAllByWallet(Wallet wallet);

    Page<Transaction> findAllBySourceAccountNumberOrDestinationAccountNumber(String sender, String recipient, Pageable pageable);
}
