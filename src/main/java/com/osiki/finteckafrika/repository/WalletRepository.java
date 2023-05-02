package com.osiki.finteckafrika.repository;

import com.osiki.finteckafrika.entity.Users;
import com.osiki.finteckafrika.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet findWalletByUsers(Users users);

    Optional<Wallet> findWalletByAccountNumberAndBankName(final String accountNumber, final String bankName);

    @Query(value = "SELECT * FROM wallet_tbl WHERE user_id = ?", nativeQuery = true)
    Wallet findUsersWalletById(Long id);
}
