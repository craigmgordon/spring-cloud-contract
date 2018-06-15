package com.example.creditcardservice;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CreditCardApplicationsController {
private final RestTemplate restTemplate;

	public CreditCardApplicationsController (RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@RequestMapping(path="/credit-card-applications", method = RequestMethod.POST)
	public ApplyForCreditCardResponse applyForCreditCard(final ApplyForCreditCardRequest applyForCreditCardRequest) {
		final int citizenNumber = applyForCreditCardRequest.getCitizenNumber();
		final CreditCheckResponse creditCheckResponse = restTemplate.postForObject("http://localhost:8080/credit-scores", new CreditCheckRequest(citizenNumber), CreditCheckResponse.class);
		if(creditCheckResponse.getScore()== CreditCheckResponse.Score.HIGH && applyForCreditCardRequest.getCardType()==ApplyForCreditCardRequest.CardType.GOLD) {
			return new ApplyForCreditCardResponse(ApplyForCreditCardResponse.Status.GRANTED);
		}
		throw new RuntimeException("Card and score not yet implemented");
	}
	
}
