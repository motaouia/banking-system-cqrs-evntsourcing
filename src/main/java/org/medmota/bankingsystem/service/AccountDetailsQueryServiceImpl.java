package org.medmota.bankingsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.medmota.bankingsystem.entities.AccountDetailsQueryEntity;
import org.medmota.bankingsystem.repositories.AccountDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailsQueryServiceImpl implements AccountDetailsQueryService {

	private final EventStore eventStore;
	private final AccountDetailsRepository accountRepository;

	public AccountDetailsQueryServiceImpl(EventStore eventStore,
			 AccountDetailsRepository accountRepository) {
		this.eventStore = eventStore;
		this.accountRepository = accountRepository;
	}

	@Override
	public List<Object> listEventsForAccount(String accountNumber) {
		return eventStore.readEvents(accountNumber).asStream().map(s -> s.getPayload()).collect(Collectors.toList());
	}

	@Override
	public AccountDetailsQueryEntity getAccountDetails(String accountNumber) {
		return accountRepository.findById(accountNumber).get();
	}

}
