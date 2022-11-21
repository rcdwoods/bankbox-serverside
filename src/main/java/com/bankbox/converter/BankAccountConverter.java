package com.bankbox.converter;

import com.bankbox.domain.BankAccount;
import com.bankbox.domain.BankName;
import com.bankbox.dto.BankAccountRequest;
import com.bankbox.dto.BankAccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BankConverter.class, CostumerConverter.class})
public interface BankAccountConverter {
	@Mapping(source = "owner.id", target = "costumerId")
	@Mapping(source = "bankName", target = "bank")
	BankAccountResponse toResponse(BankAccount bankAccount);
	List<BankAccountResponse> toResponse(List<BankAccount> bankAccount);
	@Mapping(source = "costumerId", target = "owner.id")
	BankAccount toDomain(BankAccountRequest bankAccountRequest);
}
