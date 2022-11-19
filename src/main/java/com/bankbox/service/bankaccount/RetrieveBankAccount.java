package com.bankbox.service.bankaccount;

import com.bankbox.domain.BankAccount;

import java.util.List;

public interface RetrieveBankAccount {
	List<BankAccount> retrieveByUser(Long id);
	BankAccount retrieveByAgencyAndAccount(String agency, String account);
}
