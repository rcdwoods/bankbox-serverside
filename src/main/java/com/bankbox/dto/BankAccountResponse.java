package com.bankbox.dto;

import com.bankbox.domain.BankAccountType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class BankAccountResponse {
	public Long id;
	@JsonProperty("costumer_id")
	public Long costumerId;
	public BankResponse bank;
	@JsonProperty("bank_account_type")
	public BankAccountType bankAccountType;
	public String agency;
	public String account;
	@JsonProperty("pix_key")
	public String pixKey;
	public BigDecimal balance;
}
