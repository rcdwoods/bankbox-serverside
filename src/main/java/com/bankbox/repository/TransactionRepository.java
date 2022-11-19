package com.bankbox.repository;

import com.bankbox.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	@Query(value = "SELECT t, t FROM Transaction t JOIN BankAccount b on b.id = t.source.id or b.id = t.beneficiary.id WHERE b.owner.id = :id")
	List<Transaction> findBySourceOrBeneficiary(Long id);
}
