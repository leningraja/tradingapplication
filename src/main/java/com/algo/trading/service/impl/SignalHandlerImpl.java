package com.algo.trading.service.impl;

import static com.algo.trading.util.TradingAppConstants.SIGNAL_ACTION_PREFIX;
import static com.algo.trading.util.TradingAppConstants.SIGNAL_PARAM_PREFIX;
import static com.algo.trading.util.TradingAppConstants.SIGNAL_PERFORM_ACTION;
import static com.algo.trading.util.TradingAppConstants.SIGNAL_REVERSE_ACTION;
import static com.algo.trading.util.TradingAppConstants.SIGNAL_SETUP_ACTION;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.algo.trading.external.Algo;
import com.algo.trading.service.SignalHandler;
import com.algo.trading.util.ConfigUtility;

@Service
public class SignalHandlerImpl implements SignalHandler {

	private final static Logger log = LoggerFactory.getLogger(SignalHandlerImpl.class);

	@Autowired
	private ConfigUtility configUtil;

	@Override
	public void handleSignal(int signal) {
		String signalParams = configUtil.getPropertyByKey(SIGNAL_PARAM_PREFIX.concat(String.valueOf(signal)));
		String signalAction = configUtil.getPropertyByKey(SIGNAL_ACTION_PREFIX.concat(String.valueOf(signal)));
		log.info("Received Signal config parameters {}", signalParams);
		log.info("Received Signal config action {}", signalAction);

		Algo algo = new Algo();

		if (StringUtils.hasText(signalAction) && StringUtils.hasText(signalAction)) {
			switch (signalAction) {
			case SIGNAL_SETUP_ACTION: {
				algo.setUp();
				setSignalParams(signal, signalParams, algo);
				algo.performCalc();
				algo.submitToMarket();
			}
			case SIGNAL_REVERSE_ACTION: {
				algo.reverse();
				setSignalParams(signal, signalParams, algo);
				algo.submitToMarket();
			}
			case SIGNAL_PERFORM_ACTION: {
				setSignalParams(signal, signalParams, algo);
				algo.performCalc();
				algo.submitToMarket();
			}
			}
		} else {
			algo.cancelTrades();
		}
		algo.doAlgo();
	}

	private void setSignalParams(int signal, String signalParams, Algo algo) {
		String[] params = signalParams.split(",");
		for (int i = 0; i < params.length; i++) {
			algo.setAlgoParam(signal, Integer.parseInt(params[i]));
		}
	}

}
