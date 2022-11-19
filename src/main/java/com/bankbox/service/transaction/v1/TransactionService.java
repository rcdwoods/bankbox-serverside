package com.bankbox.service.transaction.v1;

import com.bankbox.constant.ExceptionMessage;
import com.bankbox.domain.Transaction;
import com.bankbox.exception.CostumerNotFoundException;
import com.bankbox.repository.TransactionRepository;
import com.bankbox.service.costumer.RetrieveCostumer;
import com.bankbox.service.transaction.ExecuteTransaction;
import com.bankbox.service.transaction.RetrieveTransaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService implements ExecuteTransaction, RetrieveTransaction {

	private final TransactionRepository transactionRepository;
	private final RetrieveCostumer retrieveCostumer;

	public TransactionService(
		TransactionRepository transactionRepository,
		RetrieveCostumer retrieveCostumer
	) {
		this.transactionRepository = transactionRepository;
		this.retrieveCostumer = retrieveCostumer;
	}

	@Override
	public List<Transaction> executeTransactions(List<Transaction> transactions) {
		transactions.forEach(Transaction::execute);
		return transactionRepository.saveAll(transactions);
	}

	@Override
	public List<Transaction> retrieveByCostumer(Long id) {
		boolean costumerExists = retrieveCostumer.existsById(id);
		if (!costumerExists) throw new CostumerNotFoundException(ExceptionMessage.COSTUMER_NOT_FOUND);
		return transactionRepository.findBySourceOrBeneficiary(id);
	}
}
