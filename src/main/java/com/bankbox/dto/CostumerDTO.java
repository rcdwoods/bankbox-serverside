package com.bankbox.dto;

import javax.validation.constraints.NotBlank;

public class CostumerDTO {
	public Long id;
	@NotBlank
	public String name;
	@NotBlank
	public String cpf;
	@NotBlank
	public String password;
}
