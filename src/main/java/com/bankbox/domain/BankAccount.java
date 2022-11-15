package com.bankbox.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Random;

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
	private String number;
	@NotNull
	private BigDecimal balance;

	public BankAccount() {

	}

	public BankAccount(Costumer owner, BankName bankName, BankAccountType type, BigDecimal balance, String agency, String number) {
		this.owner = owner;
		this.bankName = bankName;
		this.type = type;
		this.balance = balance;
		this.agency = agency;
		this.number = number;
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

	public BankAccountType getType() {
		return type;
	}

	public String getAgency() {
		return agency;
	}

	public String getNumber() {
		return number;
	}

	public BigDecimal getBalance() {
		return balance;
	}
}
