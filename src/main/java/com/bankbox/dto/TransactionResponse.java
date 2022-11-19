package com.bankbox.dto;

import com.bankbox.domain.TransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class TransactionResponse {

	public Long id;
	@JsonProperty("source_id")
	public Long sourceId;
	@JsonProperty("beneficiary_id")
	public Long beneficiaryId;
	public TransactionType type;
	public BigDecimal value;
}
