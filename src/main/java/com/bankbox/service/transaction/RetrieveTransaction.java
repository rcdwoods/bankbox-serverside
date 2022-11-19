package com.bankbox.service.transaction;

import com.bankbox.domain.Transaction;

import java.util.List;

public interface RetrieveTransaction {
	List<Transaction> retrieveByCostumer(Long id);
}
