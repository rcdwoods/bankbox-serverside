package com.bankbox.converter;

import com.bankbox.domain.BankAccountType;
import com.bankbox.domain.Costumer;
import com.bankbox.dto.BalanceDetailsResponse;
import com.bankbox.dto.CostumerBasicDTO;
import com.bankbox.dto.CostumerDTO;
import com.bankbox.dto.CostumerRegisterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = BankAccountType.class)
public interface CostumerConverter {
	@Mapping(expression = "java(costumer.getFirstName())", target = "firstName")
	public CostumerBasicDTO toBasic(Costumer costumer);
	@Mapping(expression = "java(costumer.getFirstName())", target = "firstName")
	public CostumerDTO toDTO(Costumer costumer);
	public Costumer toCostumer(CostumerRegisterDTO costumerRegisterDTO);
	@Mapping(expression = "java(costumer.getBalance())", target = "totalBalance")
	@Mapping(expression = "java(costumer.getBalanceFrom(BankAccountType.CHECKING))", target = "checkingBalance")
	@Mapping(expression = "java(costumer.getBalanceFrom(BankAccountType.SAVINGS))", target = "savingsBalance")
	public BalanceDetailsResponse toBalanceDetails(Costumer costumer);
}
