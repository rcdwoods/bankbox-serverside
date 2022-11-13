package com.bankbox.service.costumer;

import com.bankbox.domain.Costumer;

import java.util.List;
import java.util.Optional;

public interface RetrieveCostumer {
	public List<Costumer> retrieveAll();
	public Costumer retrieveById(Long id);
}
