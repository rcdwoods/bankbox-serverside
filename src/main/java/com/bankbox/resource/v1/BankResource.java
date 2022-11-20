package com.bankbox.resource.v1;

import com.bankbox.converter.BankConverter;
import com.bankbox.domain.BankName;
import com.bankbox.dto.BankResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/banks")
public class BankResource {

	private final BankConverter bankConverter;

	public BankResource(BankConverter bankConverter) {
		this.bankConverter = bankConverter;
	}

	@GetMapping
	public ResponseEntity<List<BankResponse>> retrieveBanks(@RequestParam(required = false) String name) {
		return ResponseEntity.ok(orquestrateRetriving(name));
	}

	private List<BankResponse> orquestrateRetriving(final String name) {
		List<BankName> banks = List.of(BankName.values());
		if (name != null) banks = banks.stream()
			.filter(bank -> bank.getFormattedName().toLowerCase().startsWith(name.toLowerCase()))
			.collect(Collectors.toList());
		return bankConverter.toResponse(banks);
	}
}
