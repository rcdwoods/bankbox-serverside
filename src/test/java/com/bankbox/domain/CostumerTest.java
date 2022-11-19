package com.bankbox.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CostumerTest {

	@Test
	void mustReturnTotalBalance() {
		Costumer costumer = new Costumer();
		costumer.addBankAccount(factoryBankAccount(costumer, 733.22));
		costumer.addBankAccount(factoryBankAccount(costumer, 815.97));
		costumer.addBankAccount(factoryBankAccount(costumer, -18.99));

		Assertions.assertThat(costumer.getBalance()).isEqualTo("1530.20");
	}

	@Test
	void mustTransferMoneyBetweenCostumersWhenThereAreOneSourceAccount() {
		Costumer source = new Costumer();
		BankAccount sourceAccount = factoryBankAccount(source, 845.18);
		source.addBankAccount(sourceAccount);
		Costumer beneficiary = new Costumer();
		BankAccount beneficiaryAccount = factoryBankAccount(beneficiary, 99.23);
		beneficiary.addBankAccount(beneficiaryAccount);

		BankTransference bankTransference = new BankTransference(sourceAccount, beneficiaryAccount, BigDecimal.valueOf(242.13));

		source.transfer(List.of(bankTransference));

		Assertions.assertThat(source.getBalance()).isEqualTo("603.05");
		Assertions.assertThat(beneficiary.getBalance()).isEqualTo("341.36");
	}

	@Test
	void mustTransferMoneyBetweenCostumersWhenThereAreTwoSourceAccounts() {
		Costumer source = new Costumer();
		BankAccount sourceAccount = factoryBankAccount(source, 845.18);
		source.addBankAccount(sourceAccount);
		Costumer beneficiary = new Costumer();
		BankAccount beneficiaryAccount = factoryBankAccount(beneficiary, 99.23);
		beneficiary.addBankAccount(beneficiaryAccount);

		BankTransference transferenceOne = new BankTransference(sourceAccount, beneficiaryAccount, BigDecimal.valueOf(242.13));
		BankTransference transferenceTwo = new BankTransference(sourceAccount, beneficiaryAccount, BigDecimal.valueOf(511.11));
		BankTransference transferenceThree = new BankTransference(sourceAccount, beneficiaryAccount, BigDecimal.valueOf(5.12));

		source.transfer(List.of(transferenceOne, transferenceTwo, transferenceThree));

		Assertions.assertThat(source.getBalance()).isEqualTo("86.82");
		Assertions.assertThat(beneficiary.getBalance()).isEqualTo("857.59");
	}

	private BankAccount factoryBankAccount(Costumer costumer, Double balance) {
		return new BankAccount(costumer, BankName.ITAU, BankAccountType.SAVINGS, BigDecimal.valueOf(balance), "1322", "1233-2");
	}

}