package com.bankbox.domain;

import com.bankbox.constant.ExceptionMessage;
import com.bankbox.exception.BalanceNotEnoughException;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	private BankAccount source;
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	private BankAccount beneficiary;
	@NotNull
	private BigDecimal value;
	@NotNull
	@Enumerated(EnumType.STRING)
	private TransactionType type;

	public Transaction() {

	}

	public Transaction(BankAccount source, BankAccount beneficiary, TransactionType type, BigDecimal value) {
		validateSourceBalanceAvailability(source, value);
		this.source = source;
		this.beneficiary = beneficiary;
		this.type = type;
		this.value = value;
	}

	public void execute() {
		this.source.transfer(beneficiary, value);
	}

	public void validateSourceBalanceAvailability(BankAccount source, BigDecimal balance) {
		if (balance.compareTo(source.getBalance()) > 0)
			throw new BalanceNotEnoughException(ExceptionMessage.BALANCE_NOT_ENOUGH);
	}

	public Long getId() {
		return id;
	}

	public BankAccount getSource() {
		return source;
	}

	public BankAccount getBeneficiary() {
		return beneficiary;
	}

	public TransactionType getType() {
		return type;
	}

	public BigDecimal getValue() {
		return value;
	}
}
