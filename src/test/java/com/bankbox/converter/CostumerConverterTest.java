package com.bankbox.converter;

import com.bankbox.domain.BankAccount;
import com.bankbox.domain.BankAccountType;
import com.bankbox.domain.BankName;
import com.bankbox.domain.Costumer;
import com.bankbox.dto.BalanceDetailsResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = CostumerConverterImpl.class)
@ExtendWith(SpringExtension.class)
class CostumerConverterTest {

	@Autowired
	private CostumerConverter costumerConverter;

	@Test
	void totalBalanceMustBeCorrect() {
		Costumer costumer = new Costumer();
		costumer.addBankAccount(factoryBankAccount(costumer, BankAccountType.SAVINGS, 1000.0));
		costumer.addBankAccount(factoryBankAccount(costumer, BankAccountType.CHECKING, 2000.0));

		BalanceDetailsResponse balanceDetails = costumerConverter.toBalanceDetails(costumer);

		Assertions.assertThat(balanceDetails.totalBalance).isEqualTo("3000.0");
	}

	@Test
	void checkingBalanceMustBeCorrect() {
		Costumer costumer = new Costumer();
		costumer.addBankAccount(factoryBankAccount(costumer, BankAccountType.SAVINGS, 1000.0));
		costumer.addBankAccount(factoryBankAccount(costumer, BankAccountType.CHECKING, 2000.0));

		BalanceDetailsResponse balanceDetails = costumerConverter.toBalanceDetails(costumer);

		Assertions.assertThat(balanceDetails.checkingBalance).isEqualTo("2000.0");
	}

	@Test
	void savingsBalanceMustBeCorrect() {
		Costumer costumer = new Costumer();
		costumer.addBankAccount(factoryBankAccount(costumer, BankAccountType.SAVINGS, 1000.0));
		costumer.addBankAccount(factoryBankAccount(costumer, BankAccountType.CHECKING, 2000.0));

		BalanceDetailsResponse balanceDetails = costumerConverter.toBalanceDetails(costumer);

		Assertions.assertThat(balanceDetails.savingsBalance).isEqualTo("1000.0");
	}

	private BankAccount factoryBankAccount(Costumer costumer, BankAccountType type, Double balance) {
		return new BankAccount(costumer, BankName.ITAU, type, BigDecimal.valueOf(balance), "1322", "1233-2");
	}
}