package com.bankbox.service.bankaccount.impl;

import com.bankbox.domain.BankAccount;
import com.bankbox.repository.BankAccountRepository;
import com.bankbox.service.bankaccount.CreateBankAccount;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService implements CreateBankAccount {

	private final BankAccountRepository bankAccountRepository;

	public BankAccountService(BankAccountRepository bankAccountRepository) {
		this.bankAccountRepository = bankAccountRepository;
	}

	@Override
	public BankAccount createBankAccount(BankAccount bankAccount) {
		return bankAccountRepository.save(bankAccount);
	}
}
