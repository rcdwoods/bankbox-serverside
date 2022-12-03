package com.bankbox.repository;

import com.bankbox.domain.BankAccount;
import com.bankbox.domain.BankName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
	List<BankAccount> findByOwnerId(Long id);
	Optional<BankAccount> findByPixKey(String pixKey);
	Optional<BankAccount> findByBankNameAndAgencyAndAccount(BankName bankName, String agency, String account);
}
