package com.sapient.demo.creditcard.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.sapient.demo.creditcard.util.LuhnValidation;

public class CreditCard {

	@NotEmpty(message = "Valid 'name' should be provided")
	private final String name;

	@LuhnValidation
	private final String cardNumber;

	@NotNull(message = "Valid 'creditLimit' should be provided")
	private final Double creditLimit;

	private final Double balance;

	public CreditCard(String name, String cardNumber, Double limit, Double balance) {
		super();
		this.name = name;
		this.cardNumber = cardNumber;
		this.creditLimit = limit;
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public Double getLimit() {
		return creditLimit;
	}

	public Double getBalance() {
		return balance;
	}
}
