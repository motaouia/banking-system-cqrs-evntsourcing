package org.medmota.bankingsystem.events;

public class AccountCreatedEvent extends BaseEvent<String> {

    public final double balance;
    public final String currency;

    public AccountCreatedEvent(String id, double balance, String currency) {
        super(id);
        this.balance = balance;
        this.currency = currency;
    }
}