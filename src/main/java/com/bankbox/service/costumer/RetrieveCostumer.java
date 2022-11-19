package com.bankbox.service.costumer;

import com.bankbox.domain.Costumer;

import java.util.List;

public interface RetrieveCostumer {
	List<Costumer> retrieveAll();
	Costumer retrieveById(Long id);
	Costumer retrieveByCpf(String cpf);
	boolean existsById(Long id);
}
