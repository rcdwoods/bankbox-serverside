package com.bankbox.service.bankaccount;

import com.bankbox.domain.BankAccount;

import java.util.List;

public interface RetrieveBankAccount {
	BankAccount retrieveById(Long id);
	BankAccount retrieveByPixKey(String pixKey);
	List<BankAccount> retrieveByUser(Long id);
	BankAccount retrieveByAgencyAndAccount(String bank, String agency, String account);
}
