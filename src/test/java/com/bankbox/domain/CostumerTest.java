package com.bankbox.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

class CostumerTest {

	@Test
	void mustReturnTotalBalance() {
		Costumer costumer = new Costumer();
		costumer.addBankAccount(factoryBankAccount(costumer, 733.22));
		costumer.addBankAccount(factoryBankAccount(costumer, 815.97));
		costumer.addBankAccount(factoryBankAccount(costumer, -18.99));

		Assertions.assertThat(costumer.getBalance()).isEqualTo("1530.20");
	}

	private BankAccount factoryBankAccount(Costumer costumer, Double balance) {
		return new BankAccount(costumer, BankName.ITAU, BankAccountType.SAVINGS, BigDecimal.valueOf(balance), "1322", "1233-2");
	}

}