package com.bankbox.converter;

import com.bankbox.domain.CreditCard;
import com.bankbox.dto.CreditCardResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CreditCardConverter {
	CreditCardResponse toResponse(CreditCard creditCard);
	List<CreditCardResponse> toResponse(List<CreditCard> creditCards);
}
