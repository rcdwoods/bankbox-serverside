package com.bankbox.converter;

import com.bankbox.domain.BankAccount;
import com.bankbox.domain.BankAccountType;
import com.bankbox.domain.BankName;
import com.bankbox.domain.Costumer;
import com.bankbox.dto.BankAccountResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BankAccountConverterImpl.class, BankConverterImpl.class})
class BankAccountConverterTest {

	@Autowired
	private BankAccountConverter bankAccountConverter;

	@Test
	void mustConvertBankNameToBankResponse() {
		BankAccount bankAccount = new BankAccount(new Costumer(), BankName.ITAU, BankAccountType.CHECKING, BigDecimal.ZERO, "123", "4567");
		BankAccountResponse response = bankAccountConverter.toResponse(bankAccount);

		Assertions.assertThat(response.bank).isNotNull();
		Assertions.assertThat(response.bank.name).isEqualTo("ITAU");
		Assertions.assertThat(response.bank.formattedName).isEqualTo("Itaú Unibanco S/A");
		Assertions.assertThat(response.bank.backgroundColor).isEqualTo("#EF761C");
		Assertions.assertThat(response.bank.imgUrl).isEqualTo("../../assets/imgs/banks/itau.png");
	}
}