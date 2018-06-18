package com.example.creditcardservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureStubRunner(ids = "com.example:creditcheckservice-producer:+:stubs:8080", workOffline=true)
public class CreditcardserviceConsumerApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void shouldGrantApplicationWhenCreditScoreIsHigh() throws Exception{
		mockMvc.perform(
				post("/credit-card-applications")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{" + 
							"\"citizenNumber\": 1234," + 
							"\"cardType\": \"GOLD\"" + 
							"}"
					))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content()
						.json("{" + 
								"\"status\":\"GRANTED\"" + 
						"}"))
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));		
		
	}
	
	@Test
	public void shouldDenyApplicationWhenCreditScoreIsLow() throws Exception{
		mockMvc.perform(
				post("/credit-card-applications")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{" + 
							"\"citizenNumber\": 4444," + 
							"\"cardType\": \"GOLD\"" + 
							"}"
					))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content()
						.json("{" + 
								"\"status\":\"DENIED\"" + 
						"}"))
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));		
		
	}

}
