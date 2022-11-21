package com.bankbox.dto;

import com.bankbox.domain.BankName;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BankAccountRequest {
	@JsonProperty("costumer_id")
	public Long costumerId;
	@JsonProperty("bank_name")
	public BankName bankName;
}
