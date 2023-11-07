package org.medmota.bankingsystem.commands;

public class DepositMoneyCommand extends BaseCommand<String> {

	public final double amount;
	public final String currency;

	public DepositMoneyCommand(String id, double amount, String currency) {
		super(id);
		this.amount = amount;
		this.currency = currency;
	}
}
