package com.bankbox.repository;

import com.bankbox.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
	List<CreditCard> findByCustomerId(Long customerId);
}
