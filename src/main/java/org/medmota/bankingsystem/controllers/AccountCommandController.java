package org.medmota.bankingsystem.controllers;

import java.util.concurrent.CompletableFuture;

import org.medmota.bankingsystem.dto.CreateAccountDTO;
import org.medmota.bankingsystem.dto.DepositMoneyDTO;
import org.medmota.bankingsystem.dto.WithdrawMoneyDTO;
import org.medmota.bankingsystem.service.AccountCommandService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/account")
@Api(value = "Banking Account Commands", description = "Banking Account Command Related Endpoints", tags = "Bank Account Commands")
public class AccountCommandController {

	private final AccountCommandService accountCommandService;

	public AccountCommandController(AccountCommandService accountCommandService) {
		this.accountCommandService = accountCommandService;
	}

	@PostMapping(value = "/create")
	public CompletableFuture<String> createAccount(@RequestBody CreateAccountDTO createAccountDTO) {
		return accountCommandService.createAccount(createAccountDTO);
	}

	@PutMapping(value = "/credits/{accountNumber}")
	public CompletableFuture<String> creditMoneyToAccount(@PathVariable(value = "accountNumber") String accountNumber,
			@RequestBody DepositMoneyDTO depositMoneyDTO) {
		return accountCommandService.depositMoneyToAccount(accountNumber, depositMoneyDTO);
	}

	@PutMapping(value = "/debits/{accountNumber}")
	public CompletableFuture<String> debitMoneyFromAccount(@PathVariable(value = "accountNumber") String accountNumber,
			@RequestBody WithdrawMoneyDTO withdrawMoneyDTO) {
		return accountCommandService.withdrawMoneyFromAccount(accountNumber, withdrawMoneyDTO);
	}

}
