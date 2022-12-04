package com.bankbox.resource.v1;

import com.bankbox.converter.CreditCardConverter;
import com.bankbox.domain.CreditCard;
import com.bankbox.dto.CreditCardResponse;
import com.bankbox.service.creditcard.RetrieveCreditCard;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

	public CreditCardResource(RetrieveCreditCard retrieveCreditCard, CreditCardConverter creditCardConverter) {
		this.retrieveCreditCard = retrieveCreditCard;
		this.creditCardConverter = creditCardConverter;
	}

	@GetMapping
	public ResponseEntity<List<CreditCardResponse>> retrieveCreditCards(@RequestParam(value = "customer_id", required = true) Long customerId) {
		List<CreditCard> creditCards = retrieveCreditCard.findAllByCustomerId(customerId);
		return ResponseEntity.ok(creditCardConverter.toResponse(creditCards));
	}
}
