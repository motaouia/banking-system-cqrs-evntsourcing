package org.medmota.bankingsystem.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AccountDetailsQueryEntity {

	@Id
	private String id;
	private double balance;
	private String currency;
	private String status;

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

}
