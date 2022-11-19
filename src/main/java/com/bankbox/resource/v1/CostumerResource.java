package com.bankbox.resource.v1;

import com.bankbox.converter.CostumerConverter;
import com.bankbox.domain.Costumer;
import com.bankbox.dto.BalanceDetailsResponse;
import com.bankbox.dto.CostumerBasicDTO;
import com.bankbox.dto.CostumerDTO;
import com.bankbox.dto.CostumerRegisterDTO;
import com.bankbox.service.costumer.CreateCostumer;
import com.bankbox.service.costumer.RetrieveCostumer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/costumers")
@CrossOrigin(origins = "*")
public class CostumerResource {

	private final RetrieveCostumer retrieveCostumer;
	private final CreateCostumer createCostumer;
	private final CostumerConverter costumerConverter;

	public CostumerResource(
		RetrieveCostumer retrieveCostumer,
		CreateCostumer createCostumer,
		CostumerConverter costumerConverter
	) {
		this.retrieveCostumer = retrieveCostumer;
		this.createCostumer = createCostumer;
		this.costumerConverter = costumerConverter;
	}

	@PostMapping
	public ResponseEntity<CostumerDTO> createCostumer(@Valid  @RequestBody CostumerRegisterDTO costumerRegisterDto) {
		Costumer costumer = costumerConverter.toCostumer(costumerRegisterDto);
		Costumer costumerCreated = createCostumer.createCostumer(costumer);
		return ResponseEntity.ok(costumerConverter.toDTO(costumerCreated));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CostumerDTO> retrieveCostumer(@PathVariable Long id) {
		Costumer costumerFound = retrieveCostumer.retrieveById(id);
		return ResponseEntity.ok(costumerConverter.toDTO(costumerFound));
	}

	@GetMapping("/{cpf}/basic")
	public ResponseEntity<CostumerBasicDTO> retrieveCostumerBasic(@PathVariable String cpf) {
		Costumer costumerFound = retrieveCostumer.retrieveByCpf(cpf);
		return ResponseEntity.ok(costumerConverter.toBasic(costumerFound));
	}

	@GetMapping("/id/balance")
	public ResponseEntity<BalanceDetailsResponse> retrieveCostumerBalanceDetails(@PathVariable Long id) {
		Costumer costumerFound = retrieveCostumer.retrieveById(id);
		return ResponseEntity.ok(costumerConverter.toBalanceDetails(costumerFound));
	}
}
