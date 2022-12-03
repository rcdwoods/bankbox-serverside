package com.bankbox.service.bankaccount.impl;

import com.bankbox.constant.ExceptionMessage;
import com.bankbox.domain.BankAccount;
import com.bankbox.domain.BankAccountType;
import com.bankbox.domain.BankName;
import com.bankbox.domain.Costumer;
import com.bankbox.exception.BankAccountNotFoundException;
import com.bankbox.exception.CostumerAlreadyHasBankException;
import com.bankbox.repository.BankAccountRepository;
import com.bankbox.service.bankaccount.PersistBankAccount;
import com.bankbox.service.bankaccount.RetrieveBankAccount;
import com.bankbox.service.costumer.RetrieveCostumer;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class BankAccountService implements PersistBankAccount, RetrieveBankAccount {

	private final BankAccountRepository bankAccountRepository;
	private final RetrieveCostumer retrieveCostumer;

	public BankAccountService(BankAccountRepository bankAccountRepository, RetrieveCostumer retrieveCostumer) {
		this.bankAccountRepository = bankAccountRepository;
		this.retrieveCostumer = retrieveCostumer;
	}

	@Override
	public BankAccount saveBankAccount(BankAccount bankAccount) {
		Costumer costumer = retrieveCostumer.retrieveById(bankAccount.getOwner().getId());
		validateCostumerDoesNotHaveBank(costumer, bankAccount.getBankName());
		BankAccount generatedBankAccount = generateFakeBankAccount(costumer, bankAccount.getBankName(), BankAccountType.CHECKING);
		return bankAccountRepository.save(generatedBankAccount);
	}

	@Override
	public BankAccount retrieveByPixKey(String pixKey) {
		Optional<BankAccount> bankAccount = bankAccountRepository.findByPixKey(pixKey);
		if (bankAccount.isEmpty()) throw new BankAccountNotFoundException(ExceptionMessage.BANK_ACCOUNT_NOT_FOUDN);
		return bankAccount.get();
	}

	private void validateCostumerDoesNotHaveBank(Costumer costumer, BankName bankName) {
		boolean costumerHaveBank = costumer.getBankAccounts().stream()
			.anyMatch(bankAccount -> bankAccount.getBankName() == bankName);
		if (costumerHaveBank)
			throw new CostumerAlreadyHasBankException(ExceptionMessage.COSTUMER_ALREADY_HAS_BANK);
	}

	@Override
	public BankAccount retrieveById(Long id) {
		Optional<BankAccount> bankAccountFound = bankAccountRepository.findById(id);
		if (bankAccountFound.isEmpty()) throw new BankAccountNotFoundException(ExceptionMessage.BANK_ACCOUNT_NOT_FOUDN);;
		return bankAccountFound.get();
	}

	@Override
	public List<BankAccount> retrieveByUser(Long id) {
		return bankAccountRepository.findByOwnerId(id);
	}

	@Override
	public BankAccount retrieveByAgencyAndAccount(String bank, String agency, String account) {
		Optional<BankAccount> bankAccountFound = bankAccountRepository.findByBankNameAndAgencyAndAccount(BankName.valueOf(bank), agency, account);
		if (bankAccountFound.isEmpty()) throw new BankAccountNotFoundException(ExceptionMessage.BANK_ACCOUNT_NOT_FOUDN);
		return bankAccountFound.get();
	}

	private BankAccount generateFakeBankAccount(Costumer owner, BankName bankName, BankAccountType type) {
		return new BankAccount(owner, bankName, type, generateRandomBalance(), generateRandomAgency(), generateRandomNumber());
	}

	private BigDecimal generateRandomBalance() {
		return new BigDecimal(BigInteger.valueOf(new Random().nextInt(100001)), 2);
	}

	private String generateRandomAgency() {
		return String.valueOf(1000 + new Random().nextInt(8999));
	}

	private String generateRandomNumber() {
		return 10000 + new Random().nextInt(89999) + "-" + new Random().nextInt(9);
	}
}
