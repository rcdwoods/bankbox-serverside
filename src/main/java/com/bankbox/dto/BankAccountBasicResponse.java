package com.bankbox.dto;

import com.bankbox.domain.BankAccountType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class BankAccountBasicResponse {
	public Long id;
	@JsonProperty("customer_first_name")
	public String customerFirstName;
	public BankResponse bank;
	public BankAccountType type;
	public String agency;
	public String account;
}
