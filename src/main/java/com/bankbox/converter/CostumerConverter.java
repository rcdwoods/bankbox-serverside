package com.bankbox.converter;

import com.bankbox.domain.Costumer;
import com.bankbox.dto.CostumerDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CostumerConverter {
	public CostumerDTO toDto(Costumer costumer);
	public List<CostumerDTO> toDto(List<Costumer> costumers);
	public Costumer toCostumer(CostumerDTO costumerDTO);
}
