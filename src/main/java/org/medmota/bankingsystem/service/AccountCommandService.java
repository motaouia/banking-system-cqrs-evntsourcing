package org.medmota.bankingsystem.service;

import java.util.concurrent.CompletableFuture;

import org.medmota.bankingsystem.dto.CreateAccountDTO;
import org.medmota.bankingsystem.dto.DepositMoneyDTO;
import org.medmota.bankingsystem.dto.WithdrawMoneyDTO;

public interface AccountCommandService {

	public CompletableFuture<String> createAccount(CreateAccountDTO createAccountDTO);
	public CompletableFuture<String> depositMoneyToAccount(String accountNum, DepositMoneyDTO depositMoneyDTO);
	public CompletableFuture<String> withdrawMoneyFromAccount(String accountNum, WithdrawMoneyDTO withdrawMoneyDTO);
}
