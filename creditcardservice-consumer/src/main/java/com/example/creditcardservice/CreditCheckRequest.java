package com.example.creditcardservice;


public class CreditCheckRequest {
	
	private int citizenNumber;
	
	public int getCitizenNumber() {
		return citizenNumber;
	}

	public CreditCheckRequest(int citizenNumber) {
		this.citizenNumber=citizenNumber;
	}
	
	
}
