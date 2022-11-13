package com.bankbox.service.costumer.impl;

import com.bankbox.constant.ExceptionMessage;
import com.bankbox.domain.Costumer;
import com.bankbox.exception.CostumerNotFoundException;
import com.bankbox.repository.CostumerRepository;
import com.bankbox.service.costumer.CreateCostumer;
import com.bankbox.service.costumer.RetrieveCostumer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
	public Costumer createCostumer(Costumer costumer) {
		return costumerRepository.save(costumer);
	}
}
