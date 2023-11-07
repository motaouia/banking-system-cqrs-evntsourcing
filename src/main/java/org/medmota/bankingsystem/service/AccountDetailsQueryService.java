package org.medmota.bankingsystem.service;

import java.util.List;

import org.medmota.bankingsystem.entities.AccountDetailsQueryEntity;

public interface AccountDetailsQueryService {
	public List<Object> listEventsForAccount(String accountNumber);
	public AccountDetailsQueryEntity getAccountDetails(String accountNumber);
}
