package com.bankbox.converter;

import com.bankbox.domain.BankAccount;
import com.bankbox.domain.BankAccountType;
import com.bankbox.domain.Costumer;
import com.bankbox.domain.Transaction;
import com.bankbox.domain.TransactionType;
import com.bankbox.dto.DateTransactionsResponse;
import com.bankbox.dto.TransactionResponse;
import com.bankbox.service.bankaccount.impl.BankAccountService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

@ContextConfiguration(
	classes = {
		TransactionConverter.class,
		BankAccountConverterImpl.class,
		BankConverterImpl.class
	}
)
@ExtendWith(SpringExtension.class)
class TransactionConverterTest {

	@Autowired
	private TransactionConverter transactionConverter;

	@MockBean
	private BankAccountService bankAccountService;

	@Test
	void deveConverterTransactionParaDateTransaction() {
		BankAccount source = factoryBankAccount("Richard Nascimento");
		BankAccount beneficiary = factoryBankAccount("El Santo");
		Transaction transaction = new Transaction(source, beneficiary, TransactionType.TRANSFERENCE, BigDecimal.ONE);
		List<DateTransactionsResponse> dateTransactionsResponse = transactionConverter.toDateTransactionResponse(List.of(transaction));
		List<TransactionResponse> transactions = dateTransactionsResponse.get(0).transactions;

		Assertions.assertThat(dateTransactionsResponse).hasSize(1);
		Assertions.assertThat(transactions).hasSize(1);
		Assertions.assertThat(transactions.get(0).source.customerFirstName).isEqualTo("Richard");
		Assertions.assertThat(transactions.get(0).beneficiary.customerFirstName).isEqualTo("El");
	}

	@Test
	void deveSepararTransactionsPorDataAoConverterParaDateTransaction() {
		BankAccount source = factoryBankAccount("Richard Nascimento");
		BankAccount beneficiary = factoryBankAccount("El Santo");
		Transaction transaction = new Transaction(source, beneficiary, TransactionType.TRANSFERENCE, BigDecimal.ONE);
		Transaction transactionTwo = new Transaction(source, beneficiary, TransactionType.TRANSFERENCE, BigDecimal.ONE);
		List<DateTransactionsResponse> dateTransactionsResponse = transactionConverter.toDateTransactionResponse(List.of(transaction, transactionTwo));
		List<TransactionResponse> transactions = dateTransactionsResponse.get(0).transactions;

		Assertions.assertThat(dateTransactionsResponse).hasSize(1);
		Assertions.assertThat(transactions).hasSize(2);
	}

	private BankAccount factoryBankAccount(String name) {
		Costumer costumer = new Costumer();
		costumer.setName(name);
		BankAccount bankAccount = new BankAccount();
		bankAccount.setType(BankAccountType.CHECKING);
		bankAccount.setOwner(costumer);
		bankAccount.setBalance(BigDecimal.valueOf(1000));
		return bankAccount;
	}
}