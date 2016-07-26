package com.kuzdowicz.exercises.stockmarketapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.kuzdowicz.exercises.stockmarketapp" })
public class AppConfig {
	
	public static final int TIME_IN_MIN_INTERVAL_FOR_CACLULATING_LAST_STOCK_PRICE = 15;

}
