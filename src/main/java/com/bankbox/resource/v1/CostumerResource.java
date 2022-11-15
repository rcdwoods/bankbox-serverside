package com.bankbox.resource.v1;

import com.bankbox.converter.CostumerConverter;
import com.bankbox.domain.Costumer;
import com.bankbox.dto.CostumerDTO;
import com.bankbox.service.costumer.impl.CostumerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/costumers")
public class CostumerResource {

	private final CostumerService costumerService;
	private final CostumerConverter costumerConverter;

	public CostumerResource(CostumerService costumerService, CostumerConverter costumerConverter) {
		this.costumerService = costumerService;
		this.costumerConverter = costumerConverter;
	}

	@PostMapping
	public ResponseEntity<CostumerDTO> createCostumer(@Valid  @RequestBody CostumerDTO costumerDto) {
		Costumer costumer = costumerConverter.toCostumer(costumerDto);
		Costumer costumerCreated = costumerService.createCostumer(costumer);
		return ResponseEntity.ok(costumerConverter.toDto(costumerCreated));
	}

	@GetMapping
	public ResponseEntity<List<CostumerDTO>> retrieveCostumers() {
		List<Costumer> costumersFound = costumerService.retrieveAll();
		return ResponseEntity.ok(costumerConverter.toDto(costumersFound));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CostumerDTO> retrieveCostumer(@RequestParam Long id) {
		Costumer costumerFound = costumerService.retrieveById(id);
		return ResponseEntity.ok(costumerConverter.toDto(costumerFound));
	}
}
