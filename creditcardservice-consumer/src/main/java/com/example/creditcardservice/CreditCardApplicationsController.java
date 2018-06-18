package com.example.creditcardservice;

import static com.example.creditcardservice.ApplyForCreditCardResponse.Status.DENIED;
import static com.example.creditcardservice.ApplyForCreditCardResponse.Status.GRANTED;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class CreditCardApplicationsController {
	
	private final RestTemplate restTemplate;
	private final String creditcheckserviceBaseUrl;

	public CreditCardApplicationsController (RestTemplate restTemplate, @Value("${creditcheckservice.baseurl}") String creditcheckserviceBaseUrl) {
		this.restTemplate = restTemplate;
		this.creditcheckserviceBaseUrl = creditcheckserviceBaseUrl;
	}

	@PostMapping("/credit-card-applications")
	public ApplyForCreditCardResponse applyForCreditCard(@RequestBody final ApplyForCreditCardRequest applyForCreditCardRequest) {
		final int citizenNumber = applyForCreditCardRequest.getCitizenNumber();
		
		final String uri = UriComponentsBuilder.fromHttpUrl(creditcheckserviceBaseUrl).path("/credit-scores").toUriString();
		
		final CreditCheckResponse creditCheckResponse = restTemplate.postForObject(uri, new CreditCheckRequest(citizenNumber), CreditCheckResponse.class);
		if(creditCheckResponse.getScore()== CreditCheckResponse.Score.HIGH && applyForCreditCardRequest.getCardType()==ApplyForCreditCardRequest.CardType.GOLD) {
			return new ApplyForCreditCardResponse(GRANTED);
		}else if(creditCheckResponse.getScore() == CreditCheckResponse.Score.LOW){
			return new ApplyForCreditCardResponse(DENIED);
		}
		throw new RuntimeException("Card and score not yet implemented");
	}
	
}
