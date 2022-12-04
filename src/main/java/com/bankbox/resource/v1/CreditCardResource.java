package com.bankbox.resource.v1;

import com.bankbox.converter.CreditCardConverter;
import com.bankbox.domain.CreditCard;
import com.bankbox.dto.CreditCardResponse;
import com.bankbox.service.creditcard.PersistCreditCard;
import com.bankbox.service.creditcard.RetrieveCreditCard;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/credit_cards")
@CrossOrigin(origins = "*")
public class CreditCardResource {

	private final RetrieveCreditCard retrieveCreditCard;
	private final CreditCardConverter creditCardConverter;
	private final PersistCreditCard persistCreditCard;

	public CreditCardResource(
		RetrieveCreditCard retrieveCreditCard,
		PersistCreditCard persistCreditCard,
		CreditCardConverter creditCardConverter
	) {
		this.retrieveCreditCard = retrieveCreditCard;
		this.persistCreditCard = persistCreditCard;
		this.creditCardConverter = creditCardConverter;
	}

	@GetMapping
	public ResponseEntity<List<CreditCardResponse>> retrieveCreditCards(@RequestParam(value = "customer_id", required = true) Long customerId) {
		List<CreditCard> creditCards = retrieveCreditCard.findAllByCustomerId(customerId);
		return ResponseEntity.ok(creditCardConverter.toResponse(creditCards));
	}

	@PostMapping("/unified")
	public ResponseEntity<CreditCardResponse> generateUnifiedCard(@RequestParam(value = "customer_id", required = true) Long customerId) {
		CreditCard unifiedCreditCard = persistCreditCard.generateUnifiedCardForCustumer(customerId);
		return ResponseEntity.ok(creditCardConverter.toResponse(unifiedCreditCard));
	}
}
