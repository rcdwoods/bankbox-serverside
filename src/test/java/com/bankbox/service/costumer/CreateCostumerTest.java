package com.bankbox.service.costumer;

import com.bankbox.domain.BankAccount;
import com.bankbox.domain.BankName;
import com.bankbox.domain.Costumer;
import com.bankbox.repository.CostumerRepository;
import com.bankbox.service.costumer.impl.CostumerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = CostumerService.class)
@ExtendWith(SpringExtension.class)
class CreateCostumerTest {

	@Autowired
	private CreateCostumer createCostumer;

	@MockBean
	private CostumerRepository costumerRepository;

	@BeforeEach
	public void setup() {
		Mockito.when(costumerRepository.save(Mockito.any())).thenAnswer(it -> it.getArgument(0));
	}

	@Test
	void mustCreateCostumer() {
		Costumer costumer = new Costumer();
		costumer.setName("Richard");
		costumer.setCpf("12345678901");

		Costumer costumerCreated = createCostumer.createCostumer(costumer);

		Assertions.assertThat(costumerCreated.getName()).isEqualTo(costumer.getName());
		Assertions.assertThat(costumerCreated.getCpf()).isEqualTo(costumer.getCpf());
	}

	@Test
	void mustCreateCostumerWithTwoBankAccounts() {
		Costumer costumer = new Costumer();
		costumer.setName("Richard");
		costumer.setCpf("12345678901");

		Costumer costumerCreated = createCostumer.createCostumer(costumer);

		Assertions.assertThat(costumerCreated.getBankAccounts()).hasSize(2);
	}

	@Test
	void mustCreateCostumerWithBankAccountsContainingValidNameAndBalances() {
		Costumer costumer = new Costumer();
		costumer.setName("Richard");
		costumer.setCpf("12345678901");

		Costumer costumerCreated = createCostumer.createCostumer(costumer);
		List<BankAccount> bankAccounts = costumerCreated.getBankAccounts();

		Assertions.assertThat(bankAccounts.get(0).getBankName()).isEqualTo(BankName.ITAU);
		Assertions.assertThat(bankAccounts.get(0).getBalance()).isGreaterThan(new BigDecimal(1));
		Assertions.assertThat(bankAccounts.get(1).getBankName()).isEqualTo(BankName.NUBANK);
		Assertions.assertThat(bankAccounts.get(1).getBalance()).isGreaterThan(new BigDecimal(1));
	}
}