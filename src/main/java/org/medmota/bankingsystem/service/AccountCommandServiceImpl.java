package org.medmota.bankingsystem.service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.medmota.bankingsystem.commands.CreateAccountCommand;
import org.medmota.bankingsystem.commands.DepositMoneyCommand;
import org.medmota.bankingsystem.commands.WithdrawMoneyCommand;
import org.medmota.bankingsystem.dto.CreateAccountDTO;
import org.medmota.bankingsystem.dto.DepositMoneyDTO;
import org.medmota.bankingsystem.dto.WithdrawMoneyDTO;
import org.springframework.stereotype.Service;

@Service
public class AccountCommandServiceImpl implements AccountCommandService {

	private final CommandGateway commandGateway;

	public AccountCommandServiceImpl(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	@Override
	public CompletableFuture<String> createAccount(CreateAccountDTO createAccountDTO) {
		return commandGateway.send(new CreateAccountCommand(UUID.randomUUID().toString(),
				createAccountDTO.getStartingBalance(), createAccountDTO.getCurrency()));
	}

	@Override
	public CompletableFuture<String> depositMoneyToAccount(String accountNumber, DepositMoneyDTO depositMoneyDTO) {
		return commandGateway.send(new DepositMoneyCommand(accountNumber, depositMoneyDTO.getCreditAmount(),
				depositMoneyDTO.getCurrency()));
	}

	@Override
	public CompletableFuture<String> withdrawMoneyFromAccount(String accountNumber, WithdrawMoneyDTO withdrawMoneyDTO) {
		return commandGateway.send(
				new WithdrawMoneyCommand(accountNumber, withdrawMoneyDTO.getAmount(), withdrawMoneyDTO.getCurrency()));
	}

}
