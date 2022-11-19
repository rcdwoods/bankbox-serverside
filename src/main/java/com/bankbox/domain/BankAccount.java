package com.bankbox.domain;

import com.bankbox.constant.ExceptionMessage;
import com.bankbox.exception.BalanceNotEnoughException;

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
public class BankAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@ManyToOne
	private Costumer owner;
	@NotNull
	@Enumerated(EnumType.STRING)
	private BankName bankName;
	@NotNull
	@Enumerated(EnumType.STRING)
	private BankAccountType type;
	@NotNull
	private String agency;
	@NotNull
	private String account;
	@NotNull
	private BigDecimal balance;

	public BankAccount() {

	}

	public BankAccount(Costumer owner, BankName bankName, BankAccountType type, BigDecimal balance, String agency, String account) {
		this.owner = owner;
		this.bankName = bankName;
		this.type = type;
		this.balance = balance;
		this.agency = agency;
		this.account = account;
	}

	public void transfer(BankAccount beneficiary, BigDecimal value) {
		withdraw(value);
		beneficiary.deposit(value);
	}

	public void withdraw(BigDecimal value) {
		if (value.compareTo(balance) > 0)
			throw new BalanceNotEnoughException(ExceptionMessage.BALANCE_NOT_ENOUGH);
		balance = balance.subtract(value);
	}

	public void deposit(BigDecimal value) {
		balance = balance.add(value);
	}

	public Long getId() {
		return id;
	}

	public Costumer getOwner() {
		return owner;
	}

	public BankName getBankName() {
		return bankName;
	}

	public BankAccountType getBankAccountType() {
		return type;
	}

	public String getAgency() {
		return agency;
	}

	public String getAccount() {
		return account;
	}

	public BigDecimal getBalance() {
		return balance;
	}
}
