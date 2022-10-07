package com.algo.trading.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ConfigUtility {

    @Autowired
    private Environment env;

    public String getPropertyByKey(String propertyKey) {
        return env.getProperty(propertyKey);
    }
}
