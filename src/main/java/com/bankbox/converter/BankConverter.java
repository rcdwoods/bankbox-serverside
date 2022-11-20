package com.bankbox.converter;

import com.bankbox.domain.BankName;
import com.bankbox.dto.BankResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BankConverter {
	@Mapping(expression = "java(bankName.name())", target = "name")
	BankResponse toResponse(BankName bankName);
	List<BankResponse> toResponse(List<BankName> banks);
}
