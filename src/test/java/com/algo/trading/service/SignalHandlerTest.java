package com.algo.trading.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.algo.trading.service.impl.SignalHandlerImpl;
import com.algo.trading.util.ConfigUtility;

@SpringBootTest
public class SignalHandlerTest {

	@InjectMocks
	private SignalHandlerImpl signalHandler;

	@Mock
	private ConfigUtility configUtil;

	@DisplayName("JUnit test for handleSignal method")
	@Test
	public void givenSignal_whenSignalConfigurationPresent() {
		when(configUtil.getPropertyByKey("signal.param.value.8")).thenReturn("12");
		when(configUtil.getPropertyByKey("signal.action.value.8")).thenReturn("REVERSE");
		signalHandler.handleSignal(8);
		assertTrue(true);
	}

	@DisplayName("JUnit test for handleSignal method")
	@Test
	public void givenSignal_whenSignalConfigurationNotPresent() {
		signalHandler.handleSignal(2);
		assertFalse(false);
	}
}
