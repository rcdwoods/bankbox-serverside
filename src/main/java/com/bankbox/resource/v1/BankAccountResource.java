package com.bankbox.resource.v1;

import com.bankbox.converter.BankAccountConverter;
import com.bankbox.domain.BankAccount;
import com.bankbox.dto.BankAccountResponse;
import com.bankbox.service.bankaccount.RetrieveBankAccount;
import com.bankbox.service.bankaccount.impl.BankAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/bank_accounts")
public class BankAccountResource {

	private final RetrieveBankAccount retrieveBankAccount;
	private final BankAccountConverter bankAccountConverter;

	public BankAccountResource(RetrieveBankAccount retrieveBankAccount, BankAccountConverter bankAccountConverter) {
		this.retrieveBankAccount = retrieveBankAccount;
		this.bankAccountConverter = bankAccountConverter;
	}

	@GetMapping
	public ResponseEntity<List<BankAccountResponse>> getBankAccounts(@RequestParam("user_id") Long userId) {
		List<BankAccount> bankAccountsFound = retrieveBankAccount.retrieveByUser(userId);
		return ResponseEntity.ok(bankAccountConverter.toResponse(bankAccountsFound));
	}

	@GetMapping
	public ResponseEntity<BankAccountResponse> getBankAccount(
		@RequestParam("agency") String agency,
		@RequestParam("account") String account
	) {
		BankAccount bankAccountFound = retrieveBankAccount.retrieveByAgencyAndAccount(agency, account);
		return ResponseEntity.ok(bankAccountConverter.toResponse(bankAccountFound));
	}
}
