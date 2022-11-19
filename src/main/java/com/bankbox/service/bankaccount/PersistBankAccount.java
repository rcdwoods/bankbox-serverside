package com.bankbox.service.bankaccount;

import com.bankbox.domain.BankAccount;

public interface PersistBankAccount {
	public BankAccount saveBankAccount(BankAccount bankAccount);
}
