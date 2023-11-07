package org.medmota.bankingsystem.events;

public class WithdrawMoneyEvent extends BaseEvent<String> {

    public final double debitAmount;
    public final String currency;

    public WithdrawMoneyEvent(String id, double debitAmount, String currency) {
        super(id);
        this.debitAmount = debitAmount;
        this.currency = currency;
    }
}
