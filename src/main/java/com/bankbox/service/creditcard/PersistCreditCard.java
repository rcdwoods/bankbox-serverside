package com.bankbox.service.creditcard;

import com.bankbox.domain.CreditCard;

public interface PersistCreditCard {
	CreditCard generateUnifiedCardForCustumer(Long customerId);
}
