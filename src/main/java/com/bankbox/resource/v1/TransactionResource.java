package com.bankbox.resource.v1;

import com.bankbox.converter.TransactionConverter;
import com.bankbox.domain.Transaction;
import com.bankbox.dto.DateTransactionsResponse;
import com.bankbox.dto.TransactionRequest;
import com.bankbox.dto.TransactionResponse;
import com.bankbox.service.transaction.ExecuteTransaction;
import com.bankbox.service.transaction.RetrieveTransaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/transactions")
public class TransactionResource {

	private final RetrieveTransaction retrieveTransaction;
	private final ExecuteTransaction executeTransaction;
	private final TransactionConverter transactionConverter;

	public TransactionResource(
		RetrieveTransaction retrieveTransaction,
		ExecuteTransaction executeTransaction,
		TransactionConverter transactionConverter
	) {
		this.retrieveTransaction = retrieveTransaction;
		this.executeTransaction = executeTransaction;
		this.transactionConverter = transactionConverter;
	}

	@GetMapping
	public ResponseEntity<List<DateTransactionsResponse>> getTransactions(@RequestParam(value = "costumer_id", required = true) Long id) {
		List<Transaction> transactions = retrieveTransaction.retrieveByCostumer(id);
		return ResponseEntity.ok(transactionConverter.toDateTransactionResponse(transactions, id));
	}

	@PostMapping
	public ResponseEntity<List<TransactionResponse>> doTransactions(@Valid @RequestBody List<TransactionRequest> requests) {
		List<Transaction> convertedTransactions = transactionConverter.toModel(requests);
		Long sourceCustomerId = convertedTransactions.get(0).getSource().getOwner().getId();
		List<Transaction> executedTransactions = executeTransaction.executeTransactions(convertedTransactions);
		return ResponseEntity.ok(transactionConverter.toResponse(executedTransactions, sourceCustomerId));
	}
}
