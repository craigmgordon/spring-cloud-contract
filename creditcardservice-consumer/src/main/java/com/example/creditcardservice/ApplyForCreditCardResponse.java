package com.example.creditcardservice;


public class ApplyForCreditCardResponse {
	private final Status status;
	
	public Status getStatus() {
		return status;
	}

	public ApplyForCreditCardResponse(Status status) {
		this.status = status;
	}
	
	public enum Status{
		GRANTED
	}
}
