package com.algo.trading.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.algo.trading.service.SignalHandler;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SignalController.class)
public class SignalControllerTest {

	@Autowired
    private MockMvc mockMvc;

	@MockBean
	private SignalHandler signalHandler;
	
	@Test
	public void canRetrieveByIdWhenExists() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/tradingSignal/{signalId}", 3).accept(
				MediaType.APPLICATION_JSON);
		
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
	}

}
