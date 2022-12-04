package com.bankbox.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.YearMonth;

public class CreditCardResponse {
	public Long id;
	public String owner;
	public String number;
	public YearMonth expiration;
	@JsonProperty("last_numbers")
	public String lastNumbers;
	public int securityNumber;
	public String type;
	public String brand;
}
