package org.medmota.bankingsystem.evnetssources.entities;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.medmota.bankingsystem.commands.CreateAccountCommand;
import org.medmota.bankingsystem.commands.DepositMoneyCommand;
import org.medmota.bankingsystem.commands.WithdrawMoneyCommand;
import org.medmota.bankingsystem.events.AccountCreatedEvent;
import org.medmota.bankingsystem.events.ActivateAccountEvent;
import org.medmota.bankingsystem.events.DepositMoneyEvent;
import org.medmota.bankingsystem.events.HoldAccountEvent;
import org.medmota.bankingsystem.events.InactiveAccountEvent;
import org.medmota.bankingsystem.events.WithdrawMoneyEvent;

@Aggregate
//@Aggregate annotation indicates that this entity will be managed by Axon. 
public class BankAccountAggregate {

	@AggregateIdentifier
	/*
	 * @AggregateIdentifier annotation is used to identify particular instance of
	 * Aggregate. It is similar to the @Id annotation in JPA.
	 */
	private String id;
	private double balance;
	private String currency;
	private String status;
	private String errorMsg;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@CommandHandler
    public BankAccountAggregate(CreateAccountCommand createAccountCommand){
        AggregateLifecycle.apply(new AccountCreatedEvent(createAccountCommand.id, createAccountCommand.accountBalance, createAccountCommand.currency));
    }

	@EventSourcingHandler
	protected void on(AccountCreatedEvent accountCreatedEvent) {
		this.id = accountCreatedEvent.id;
		this.balance = accountCreatedEvent.balance;
		this.currency = accountCreatedEvent.currency;
		this.status = "CREATED";
		AggregateLifecycle.apply(new ActivateAccountEvent(this.id, "ACTIVATED"));

	}

	@CommandHandler
	protected void on(DepositMoneyCommand depositMoneyCommand) {
		AggregateLifecycle.apply(new DepositMoneyEvent(depositMoneyCommand.id, depositMoneyCommand.amount,
				depositMoneyCommand.currency));
	}

	@EventSourcingHandler
	protected void on(DepositMoneyEvent depositMoneyEvent) {

		if (this.balance < 0 & (this.balance + depositMoneyEvent.creditAmount) >= 0) {
			if (this.status != "ACTIVATED") {
				AggregateLifecycle.apply(new ActivateAccountEvent(depositMoneyEvent.id, "ACTIVATED"));

			}
		}

		this.balance += depositMoneyEvent.creditAmount;
	}

	@CommandHandler
	protected void on(WithdrawMoneyCommand withdrawMoneyCommand) {
		AggregateLifecycle.apply(new WithdrawMoneyEvent(withdrawMoneyCommand.id, withdrawMoneyCommand.amount,
				withdrawMoneyCommand.currency));
	}

	@EventSourcingHandler
	protected void on(WithdrawMoneyEvent withdrawMoneyEvent) {
		if (this.status != "ACTIVATED") {
			AggregateLifecycle.apply(new InactiveAccountEvent(withdrawMoneyEvent.id, "inactive account"));
			return;
		}

		if (this.balance >= 0 & (this.balance - withdrawMoneyEvent.debitAmount) < 0) {
			AggregateLifecycle.apply(new HoldAccountEvent(withdrawMoneyEvent.id, "HOLD", "insufficient balance"));
		} else {
			this.balance -= withdrawMoneyEvent.debitAmount;
		}

	}

	@EventSourcingHandler
	protected void on(ActivateAccountEvent activateAccountEvent) {
		this.status = activateAccountEvent.status;
	}

	@EventSourcingHandler
	protected void on(HoldAccountEvent holdAccountEvent) {
		this.status = holdAccountEvent.status;
		this.errorMsg = holdAccountEvent.errorMsg;
	}

	@EventSourcingHandler
	protected void on(InactiveAccountEvent inactiveAccountEvent) {
		this.errorMsg = inactiveAccountEvent.errorMsg;
	}

}
