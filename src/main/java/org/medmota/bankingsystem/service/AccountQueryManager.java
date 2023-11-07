package org.medmota.bankingsystem.service;

import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.medmota.bankingsystem.entities.AccountDetailsQueryEntity;
import org.medmota.bankingsystem.events.BaseEvent;
import org.medmota.bankingsystem.evnetssources.entities.BankAccountAggregate;
import org.medmota.bankingsystem.repositories.AccountDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AccountQueryManager {
	@Autowired
	private AccountDetailsRepository accountDetailsRepository;

	@Autowired
	@Qualifier("accountAggregateRepository")
	private EventSourcingRepository<BankAccountAggregate> accountAggregateRepository;

	@EventSourcingHandler
	void on(BaseEvent event) {
		persistAccount(buildQueryAccount(getAccountFromEvent(event)));
	}

	private AccountDetailsQueryEntity buildQueryAccount(BankAccountAggregate accountAggregate) {
		AccountDetailsQueryEntity accountQueryEntity = findExistingOrCreateQueryAccount(accountAggregate.getId());

		accountQueryEntity.setId(accountAggregate.getId());
		accountQueryEntity.setBalance(accountAggregate.getBalance());
		accountQueryEntity.setCurrency(accountAggregate.getCurrency());
		accountQueryEntity.setStatus(accountAggregate.getStatus());

		return accountQueryEntity;
	}

	private BankAccountAggregate getAccountFromEvent(BaseEvent event) {
		return accountAggregateRepository.load(event.id.toString()).getWrappedAggregate().getAggregateRoot();
	}

	private AccountDetailsQueryEntity findExistingOrCreateQueryAccount(String id) {
		return accountDetailsRepository.findById(id).isPresent() ? accountDetailsRepository.findById(id).get()
				: new AccountDetailsQueryEntity();
	}

	private void persistAccount(AccountDetailsQueryEntity accountQueryEntity) {
		accountDetailsRepository.save(accountQueryEntity);
	}
}
