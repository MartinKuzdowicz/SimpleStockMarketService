package com.kuzdowicz.stockmarketapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.kuzdowicz.stockmarketapp.config.AppConfig;
import com.kuzdowicz.stockmarketapp.services.StockMarketTradingService;

public class StockMarketApplication {

	private static ApplicationContext application;

	public static void main(String[] args) {

		application = new AnnotationConfigApplicationContext(AppConfig.class);
		StockMarketTradingService stockTradingService = application.getBean(StockMarketTradingService.class);

		System.out.println("+--------+----------------+-----------+-------------+");
		System.out.println("| Symbol | Dividend yield | P/E ratio | Stock price |");
		System.out.println("+--------+----------------+-----------+-------------+");

		stockTradingService.getAllStocks().forEach(s -> System.out.println(s.getTickerSymbol()));

	}

}
