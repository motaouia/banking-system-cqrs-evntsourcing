package org.medmota.bankingsystem.evnetssources.entities;

import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class AccountEntityAggregate {

	@AggregateIdentifier
    private String id;
    private double balance;
    private String currency;
    private String status;
}
