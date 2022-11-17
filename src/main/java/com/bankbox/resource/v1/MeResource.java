package com.bankbox.resource.v1;

import com.bankbox.converter.CostumerConverter;
import com.bankbox.domain.Costumer;
import com.bankbox.dto.CostumerDTO;
import com.bankbox.service.costumer.RetrieveCostumer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/v1/me")
@CrossOrigin(origins = "*")
public class MeResource {

	private final RetrieveCostumer retrieveCostumer;
	private final CostumerConverter costumerConverter;

	public MeResource(RetrieveCostumer retrieveCostumer, CostumerConverter costumerConverter) {
		this.retrieveCostumer = retrieveCostumer;
		this.costumerConverter = costumerConverter;
	}

	@GetMapping
	public ResponseEntity<CostumerDTO> me(Principal principal) {
		String authenticatedCpf = principal.getName();
		Costumer costumerFound = retrieveCostumer.retrieveByCpf(authenticatedCpf);
		return ResponseEntity.ok(costumerConverter.toDTO(costumerFound));
	}
}
