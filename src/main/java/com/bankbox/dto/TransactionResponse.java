package com.bankbox.dto;

import com.bankbox.domain.TransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponse {

	public Long id;
	@JsonProperty("source_id")
	public Long sourceId;
	@JsonProperty("beneficiary_id")
	public Long beneficiaryId;
	public BankAccountBasicResponse source;
	public BankAccountBasicResponse beneficiary;
	public TransactionType type;
	public BigDecimal value;
	@JsonProperty("performed_at")
	public LocalDateTime performedAt;
}
