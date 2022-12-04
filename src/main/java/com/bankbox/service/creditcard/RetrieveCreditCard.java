package com.bankbox.service.creditcard;

import com.bankbox.domain.CreditCard;

import java.util.List;

public interface RetrieveCreditCard {
	List<CreditCard> findAllByCustomerId(Long customerId);
}
