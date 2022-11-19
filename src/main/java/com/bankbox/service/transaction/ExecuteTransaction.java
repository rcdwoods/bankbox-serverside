package com.bankbox.service.transaction;

import com.bankbox.domain.Transaction;

import java.util.List;

public interface ExecuteTransaction {
	List<Transaction> executeTransactions(List<Transaction> transactions);
}
