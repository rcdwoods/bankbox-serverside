package com.bankbox.service.creditcard.impl;

import com.bankbox.domain.CreditCard;
import com.bankbox.repository.CreditCardRepository;
import com.bankbox.service.creditcard.RetrieveCreditCard;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardService implements RetrieveCreditCard {

	private final CreditCardRepository creditCardRepository;

	public CreditCardService(CreditCardRepository creditCardRepository) {
		this.creditCardRepository = creditCardRepository;
	}

	@Override
	public List<CreditCard> findAllByCustomerId(Long customerId) {
		return creditCardRepository.findByCustomerId(customerId);
	}
}
