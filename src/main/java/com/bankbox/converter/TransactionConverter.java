package com.bankbox.converter;

import com.bankbox.domain.BankAccount;
import com.bankbox.domain.Transaction;
import com.bankbox.domain.TransactionType;
import com.bankbox.dto.TransactionRequest;
import com.bankbox.dto.TransactionResponse;
import com.bankbox.service.bankaccount.RetrieveBankAccount;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionConverter {
	private final RetrieveBankAccount retrieveBankAccount;

	public TransactionConverter(RetrieveBankAccount retrieveBankAccount) {
		this.retrieveBankAccount = retrieveBankAccount;
	}

	public List<Transaction> toModel(List<TransactionRequest> requests) {
		return requests.stream()
			.map(this::toModel)
			.collect(Collectors.toList());
	}

	public Transaction toModel(TransactionRequest request) {
		BankAccount source = retrieveBankAccount.retrieveById(request.sourceId);
		BankAccount beneficiary = retrieveBankAccount.retrieveById(request.beneficiaryId);
		return new Transaction(source, beneficiary, TransactionType.valueOf(request.type), request.value);
	}

	public List<TransactionResponse> toResponse(List<Transaction> transactions) {
		return transactions.stream()
			.map(this::toResponse)
			.collect(Collectors.toList());
	}

	public TransactionResponse toResponse(Transaction transaction) {
		TransactionResponse response = new TransactionResponse();
		response.id = transaction.getId();
		response.sourceId = transaction.getSource().getId();
		response.beneficiaryId = transaction.getBeneficiary().getId();
		response.type = transaction.getType();
		response.value = transaction.getValue();
		return response;
	}
}
