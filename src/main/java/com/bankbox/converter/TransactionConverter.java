package com.bankbox.converter;

import com.bankbox.domain.BankAccount;
import com.bankbox.domain.Transaction;
import com.bankbox.domain.TransactionType;
import com.bankbox.dto.BankAccountBasicResponse;
import com.bankbox.dto.DateTransactionsResponse;
import com.bankbox.dto.TransactionRequest;
import com.bankbox.dto.TransactionResponse;
import com.bankbox.service.bankaccount.RetrieveBankAccount;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class TransactionConverter {
	private final RetrieveBankAccount retrieveBankAccount;
	private final BankConverter bankConverter;
	private final BankAccountConverter bankAccountConverter;

	public TransactionConverter(
		RetrieveBankAccount retrieveBankAccount,
		BankConverter bankConverter,
		BankAccountConverter bankAccountConverter
	) {
		this.retrieveBankAccount = retrieveBankAccount;
		this.bankConverter = bankConverter;
		this.bankAccountConverter = bankAccountConverter;
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
		response.source = bankAccountConverter.toBasicResponse(transaction.getSource());
		response.beneficiary = bankAccountConverter.toBasicResponse(transaction.getBeneficiary());
		response.type = transaction.getType();
		response.value = transaction.getValue();
		response.performedAt = transaction.getPerformedAt();
		return response;
	}

	public List<DateTransactionsResponse> toDateTransactionResponse(List<Transaction> transactions) {
		List<TransactionResponse> transactionsConverted = toResponse(transactions);
		Map<LocalDate, List<TransactionResponse>> map = mapTransactionsByDate(transactionsConverted);
		List<DateTransactionsResponse> dateTransactions = new ArrayList<>();
		map.forEach((performedAt, transactionsResponse) -> dateTransactions.add(new DateTransactionsResponse(performedAt, transactionsResponse)));
		return dateTransactions;
	}

	private Map<LocalDate, List<TransactionResponse>> mapTransactionsByDate(List<TransactionResponse> transactions) {
		Map<LocalDate, List<TransactionResponse>> map = new HashMap<>();
		transactions.forEach(it -> {
			List<TransactionResponse> actualList = map.get(it.performedAt.toLocalDate());
			if (Objects.isNull(actualList)) actualList = new ArrayList<>();
			actualList.add(it);
			map.put(it.performedAt.toLocalDate(), actualList);
		});
		return map;
	}
}
