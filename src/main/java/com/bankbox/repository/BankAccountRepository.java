package com.bankbox.repository;

import com.bankbox.domain.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
	List<BankAccount> findByOwnerId(Long id);
	Optional<BankAccount> findByAgencyAndAccount(String agency, String account);
}
