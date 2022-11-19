package com.bankbox.converter;

import com.bankbox.domain.BankAccount;
import com.bankbox.dto.BankAccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BankAccountConverter {
	@Mapping(source = "costumer.id", target = "costumerId")
	BankAccountResponse toResponse(BankAccount bankAccount);
	List<BankAccountResponse> toResponse(List<BankAccount> bankAccount);
}
