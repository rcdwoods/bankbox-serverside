package com.bankbox.domain;

import com.bankbox.constant.ExceptionMessage;
import com.bankbox.exception.BalanceNotEnoughException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

	@Test
	void mustTransferMoneyBetweenCostumersWhenThereAreOneSourceAccount() {
		Costumer source = new Costumer();
		BankAccount sourceAccount = factoryBankAccount(new Costumer(), 845.18);
		source.addBankAccount(sourceAccount);
		Costumer beneficiary = new Costumer();
		BankAccount beneficiaryAccount = factoryBankAccount(beneficiary, 99.23);
		beneficiary.addBankAccount(beneficiaryAccount);

		Transaction transaction = new Transaction(sourceAccount, beneficiaryAccount, TransactionType.PIX, BigDecimal.valueOf(242.13));

		transaction.execute();

		Assertions.assertThat(source.getBalance()).isEqualTo("603.05");
		Assertions.assertThat(beneficiary.getBalance()).isEqualTo("341.36");
	}

	@Test
	void mustThrowExceptionWhenSourceDoesNotHaveEnoughBalance() {
		Costumer source = new Costumer();
		BankAccount sourceAccount = factoryBankAccount(new Costumer(), 845.18);
		source.addBankAccount(sourceAccount);
		Costumer beneficiary = new Costumer();
		BankAccount beneficiaryAccount = factoryBankAccount(beneficiary, 99.23);
		beneficiary.addBankAccount(beneficiaryAccount);

		Exception exception = assertThrows(BalanceNotEnoughException.class, () -> new Transaction(sourceAccount, beneficiaryAccount, TransactionType.PIX, BigDecimal.valueOf(845.19)));

		Assertions.assertThat(exception.getMessage()).isEqualTo(ExceptionMessage.BALANCE_NOT_ENOUGH);
	}

	private BankAccount factoryBankAccount(Costumer costumer, Double balance) {
		return new BankAccount(costumer, BankName.ITAU, BankAccountType.SAVINGS, BigDecimal.valueOf(balance), "1322", "1233-2");
	}
}