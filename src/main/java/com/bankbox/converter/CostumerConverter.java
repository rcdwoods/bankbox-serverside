package com.bankbox.converter;

import com.bankbox.domain.Costumer;
import com.bankbox.dto.CostumerBasicDTO;
import com.bankbox.dto.CostumerDTO;
import com.bankbox.dto.CostumerRegisterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CostumerConverter {
	@Mapping(expression = "java(costumer.getFirstName())", target = "firstName")
	public CostumerBasicDTO toBasic(Costumer costumer);
	@Mapping(expression = "java(costumer.getFirstName())", target = "firstName")
	public CostumerDTO toDTO(Costumer costumer);
	public Costumer toCostumer(CostumerRegisterDTO costumerRegisterDTO);
}
