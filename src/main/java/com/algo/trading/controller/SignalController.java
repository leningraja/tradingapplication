package com.algo.trading.controller;

import static com.algo.trading.util.TradingAppConstants.SUCCESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.algo.trading.service.SignalHandler;
 
@RestController
@RequestMapping("/tradingSignal")
public class SignalController 
{
    @Autowired
    SignalHandler service;
 
    /**
     * This REST service method is used to call the signal service
     * @param signalId
     * @return String
     */
    @GetMapping("/{signalId}")
    @ResponseBody
    public String getEmployeeById(@PathVariable("signalId") int signalId) {
        service.handleSignal(signalId);
        return  SUCCESS;
    }

 
}
