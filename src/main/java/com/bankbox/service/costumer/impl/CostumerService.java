package com.bankbox.service.impl;

import com.bankbox.domain.Costumer;
import com.bankbox.repository.CostumerRepository;
import com.bankbox.service.CreateCostumer;
import com.bankbox.service.RetrieveCostumer;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CostumerService implements RetrieveCostumer, CreateCostumer {

	private final CostumerRepository costumerRepository;

	public CostumerService(CostumerRepository costumerRepository) {
		this.costumerRepository = costumerRepository;
	}

	@Override
	public Optional<Costumer> retrieveCostumer() {
		return Optional.empty();
	}

	@Override
	public Costumer createCostumer(Costumer costumer) {
		return costumerRepository.save(costumer);
	}
}
