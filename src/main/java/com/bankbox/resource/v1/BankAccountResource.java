package com.bankbox.resource.v1;

import com.bankbox.converter.BankAccountConverter;
import com.bankbox.domain.BankAccount;
import com.bankbox.dto.BankAccountRequest;
import com.bankbox.dto.BankAccountResponse;
import com.bankbox.service.bankaccount.PersistBankAccount;
import com.bankbox.service.bankaccount.RetrieveBankAccount;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/bank_accounts")
public class BankAccountResource {

	private final PersistBankAccount persistBankAccount;
	private final RetrieveBankAccount retrieveBankAccount;
	private final BankAccountConverter bankAccountConverter;

	public BankAccountResource(
		PersistBankAccount persistBankAccount,
		RetrieveBankAccount retrieveBankAccount,
		BankAccountConverter bankAccountConverter
	) {
		this.persistBankAccount = persistBankAccount;
		this.retrieveBankAccount = retrieveBankAccount;
		this.bankAccountConverter = bankAccountConverter;
	}

	@GetMapping
	public ResponseEntity<List<BankAccountResponse>> getBankAccounts(
		@RequestParam(value = "user_id", required = false) Long userId,
		@RequestParam(value = "agency", required = false) String agency,
		@RequestParam(value = "account", required = false) String account
	) {
		List<BankAccount> bankAccountsFound = orquestrateRetriving(userId, agency, account);
		return ResponseEntity.ok(bankAccountConverter.toResponse(bankAccountsFound));
	}

	@PostMapping
	public ResponseEntity<BankAccountResponse> addBank(@RequestBody BankAccountRequest request) {
		BankAccount bankAccount = bankAccountConverter.toDomain(request);
		BankAccount createdBankAccount = persistBankAccount.saveBankAccount(bankAccount);
		return ResponseEntity.ok(bankAccountConverter.toResponse(createdBankAccount));
	}

	private List<BankAccount> orquestrateRetriving(Long userId, String agency, String account) {
		if (userId != null) return retrieveBankAccount.retrieveByUser(userId);
		if (agency != null && account != null) return List.of(retrieveBankAccount.retrieveByAgencyAndAccount(agency, account));
		return List.of();
	}
}
