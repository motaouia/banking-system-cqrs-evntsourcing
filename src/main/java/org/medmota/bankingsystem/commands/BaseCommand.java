package org.medmota.bankingsystem.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class BaseCommand<T> {
	/*
	 This identifier is used by Axon to determine which instance of Aggregate should 
	 handle the command.
	 */
	@TargetAggregateIdentifier
	public final T id;

	public BaseCommand(T id) {
		this.id = id;
	}
}