package org.medmota.bankingsystem.controllers;

import java.util.List;

import org.medmota.bankingsystem.entities.AccountDetailsQueryEntity;
import org.medmota.bankingsystem.service.AccountDetailsQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/account")
@Api(value = "Bank Account Queries", description = "Bank Account Query Events Endpoint", tags = "Bank Account Queries")
public class AccountQueryController {

	private final AccountDetailsQueryService accountDetailsQueryService;

	public AccountQueryController(AccountDetailsQueryService accountDetailsQueryService) {
		this.accountDetailsQueryService = accountDetailsQueryService;
	}

	@GetMapping("/{accountNumber}")
	public AccountDetailsQueryEntity getAccountDetails(@PathVariable(value = "accountNumber") String accountNumber) {
		return accountDetailsQueryService.getAccountDetails(accountNumber);
	}

	@GetMapping("/{accountNumber}/events")
	public List<Object> listEventsForAccount(@PathVariable(value = "accountNumber") String accountNumber) {
		return accountDetailsQueryService.listEventsForAccount(accountNumber);
	}

}
