package com.bankbox.service.costumer.impl;

import com.bankbox.constant.ExceptionMessage;
import com.bankbox.domain.BankAccount;
import com.bankbox.domain.BankAccountType;
import com.bankbox.domain.BankName;
import com.bankbox.domain.Costumer;
import com.bankbox.exception.CostumerAlreadyExistsException;
import com.bankbox.exception.CostumerNotFoundException;
import com.bankbox.repository.CostumerRepository;
import com.bankbox.service.costumer.CreateCostumer;
import com.bankbox.service.costumer.RetrieveCostumer;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CostumerService implements RetrieveCostumer, CreateCostumer {

	private final CostumerRepository costumerRepository;

	public CostumerService(CostumerRepository costumerRepository) {
		this.costumerRepository = costumerRepository;
	}

	@Override
	public List<Costumer> retrieveAll() {
		return costumerRepository.findAll();
	}

	@Override
	public Costumer retrieveById(Long id) {
		Optional<Costumer> costumerFound = costumerRepository.findById(id);
		if (costumerFound.isEmpty()) throw new CostumerNotFoundException(ExceptionMessage.COSTUMER_NOT_FOUND);
		return costumerFound.get();
	}

	@Override
	public String retrieveNameByCpf(String cpf) {
		String costumerNameFound = costumerRepository.findNameByCpf(cpf);
		if (costumerNameFound == null) throw new CostumerNotFoundException(ExceptionMessage.COSTUMER_NOT_FOUND);
		return costumerNameFound;
	}

	@Override
	public Costumer createCostumer(Costumer costumer) {
		Optional<Costumer> existentCostumer = costumerRepository.findByCpf(costumer.getCpf());
		if (existentCostumer.isPresent()) throw new CostumerAlreadyExistsException(ExceptionMessage.COSTUMER_ALREADY_EXISTS);
		costumer = addRandomBankAccounts(costumer);
		return costumerRepository.save(costumer);
	}

	private Costumer addRandomBankAccounts(Costumer costumer) {
		BankAccount itau = generateFakeBankAccount(costumer, BankName.ITAU, BankAccountType.CHECKING);
		BankAccount bradesco = generateFakeBankAccount(costumer, BankName.BRADESCO, BankAccountType.SAVINGS);
		costumer.addBankAccount(itau);
		costumer.addBankAccount(bradesco);
		return costumer;
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
		return String.valueOf(10000 + new Random().nextInt(89999));
	}
}
