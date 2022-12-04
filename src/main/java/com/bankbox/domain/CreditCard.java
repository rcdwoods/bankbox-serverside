package com.bankbox.domain;

import com.bankbox.constant.Constant;

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
public class CreditCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String owner;
	@NotNull
	@ManyToOne
	private Costumer customer;
	@NotNull
	private String number;
	@NotNull
	private String expiration;
	@NotNull
	private int securityNumber;
	@NotNull
	@Enumerated(EnumType.STRING)
	private CreditCardType type;
	@NotNull
	public String brand;
	@NotNull
	public BigDecimal creditLimit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		String formattedNumber = number.replace(" ", "");
		if (formattedNumber.length() != Constant.CREDIT_CARD_LENGTH)
			throw new IllegalArgumentException("Incorrect credit card length");
		this.number = formattedNumber;
	}

	public String getLastNumbers() {
		return number.subSequence(12, 16).toString();
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

	public int getSecurityNumber() {
		return securityNumber;
	}

	public void setSecurityNumber(int securityNumber) {
		if (String.valueOf(securityNumber).length() != Constant.SECURITY_NUMBER_LENGTH)
			throw new IllegalArgumentException("Incorrect credit card security number");
		this.securityNumber = securityNumber;
	}

	public CreditCardType getType() {
		return type;
	}

	public void setType(CreditCardType type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal limit) {
		this.creditLimit = limit;
	}

	public Costumer getCustomer() {
		return customer;
	}

	public void setCustomer(Costumer customer) {
		this.customer = customer;
	}
}
