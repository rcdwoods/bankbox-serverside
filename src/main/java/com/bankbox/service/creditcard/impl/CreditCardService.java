package com.bankbox.service.creditcard.impl;

import com.bankbox.domain.Costumer;
import com.bankbox.domain.CreditCard;
import com.bankbox.domain.CreditCardType;
import com.bankbox.repository.CreditCardRepository;
import com.bankbox.service.costumer.RetrieveCostumer;
import com.bankbox.service.creditcard.PersistCreditCard;
import com.bankbox.service.creditcard.RetrieveCreditCard;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
public class CreditCardService implements RetrieveCreditCard, PersistCreditCard {

	private final CreditCardRepository creditCardRepository;
	private final RetrieveCostumer retrieveCostumer;

	public CreditCardService(CreditCardRepository creditCardRepository, RetrieveCostumer retrieveCostumer) {
		this.creditCardRepository = creditCardRepository;
		this.retrieveCostumer = retrieveCostumer;
	}

	@Override
	public List<CreditCard> findAllByCustomerId(Long customerId) {
		return creditCardRepository.findByCustomerId(customerId);
	}

	@Override
	public CreditCard generateUnifiedCardForCustumer(Long customerId) {
		Costumer customer = retrieveCostumer.retrieveById(customerId);
		Optional<CreditCard> currentUnifiedCard = customer.getCreditCards().stream()
			.filter(card -> Objects.equals(card.brand, "BANKBOX")).findFirst();
		if (currentUnifiedCard.isPresent()) return currentUnifiedCard.get();
		CreditCard unifiedCard = new CreditCard();
		unifiedCard.setOwner(customer.getName());
		unifiedCard.setNumber(generateCardNumber());
		unifiedCard.setSecurityNumber(100 + new Random().nextInt(899));
		unifiedCard.setBrand("BANKBOX");
		unifiedCard.setExpiration("2031-06");
		unifiedCard.setType(CreditCardType.VIRTUAL);
		unifiedCard.setCreditLimit(customer.getCreditCardsLimit());
		unifiedCard.setCustomer(customer);
		return creditCardRepository.save(unifiedCard);
	}

	private String generateCardNumber() {
		String number = "";
		for (int i = 0; i < 4; i++) {
			number = number.concat(String.valueOf(1000 + new Random().nextInt(8999)));
		}
		return number;
	}
}
