package org.medmota.bankingsystem.events;

public class DepositMoneyEvent extends BaseEvent<String> {

    public final double creditAmount;
    public final String currency;

    public DepositMoneyEvent(String id, double creditAmount, String currency) {
        super(id);
        this.creditAmount = creditAmount;
        this.currency = currency;
    }
}