package com.bankbox.service.bankaccount.impl;

import com.bankbox.domain.BankAccount;
import com.bankbox.repository.BankAccountRepository;
import com.bankbox.service.bankaccount.CreateBankAccount;
import com.bankbox.service.bankaccount.RetrieveBankAccount;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService implements CreateBankAccount, RetrieveBankAccount {

	private final BankAccountRepository bankAccountRepository;

	public BankAccountService(BankAccountRepository bankAccountRepository) {
		this.bankAccountRepository = bankAccountRepository;
	}

	@Override
	public BankAccount createBankAccount(BankAccount bankAccount) {
		return bankAccountRepository.save(bankAccount);
	}

	@Override
	public List<BankAccount> retrieveByUser(Long id) {
		return bankAccountRepository.findByOwnerId(id);
	}

	@Override
	public BankAccount retrieveByAgencyAndAccount(String agency, String account) {
		Optional<BankAccount> bankAccountFound = bankAccountRepository.findByAgencyAndAccount(agency, account);
		if (bankAccountFound.isEmpty()) throw new RuntimeException("Bank account not found");
		return bankAccountFound.get();
	}
}
