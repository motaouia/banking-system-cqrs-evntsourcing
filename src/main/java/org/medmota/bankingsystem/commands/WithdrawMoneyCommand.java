package org.medmota.bankingsystem.commands;

public class WithdrawMoneyCommand extends BaseCommand<String> {

    public final double amount;
    public final String currency;

    public WithdrawMoneyCommand(String id, double amount, String currency) {
        super(id);
        this.amount = amount;
        this.currency = currency;
    }
}
