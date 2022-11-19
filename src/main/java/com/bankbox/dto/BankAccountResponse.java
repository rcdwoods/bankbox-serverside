package com.bankbox.dto;

import com.bankbox.domain.BankAccountType;
import com.bankbox.domain.BankName;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class BankAccountResponse {
	public Long id;
	@JsonProperty("costumer_id")
	public Long costumerId;
	@JsonProperty("bank_name")
	public BankName bankName;
	@JsonProperty("bank_account_type")
	public BankAccountType bankAccountType;
	public String agency;
	public String account;
	public BigDecimal balance;
}
