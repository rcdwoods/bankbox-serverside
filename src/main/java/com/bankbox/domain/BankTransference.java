package com.bankbox.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class BankTransference {
	@Id
	private Long id;
	@ManyToOne
	private BankAccount source;
	@ManyToOne
	private BankAccount beneficiary;
	private BigDecimal value;

	public BankTransference(BankAccount source, BankAccount beneficiary, BigDecimal value) {
		validateSourceBalanceAvailability(source, value);
		this.source = source;
		this.beneficiary = beneficiary;
		this.value = value;
	}

	public void execute() {
		this.source.transfer(beneficiary, value);
	}

	public void validateSourceBalanceAvailability(BankAccount source, BigDecimal balance) {
		if (balance.compareTo(source.getBalance()) > 0)
			throw new RuntimeException("Balance not enought");
	}
}
