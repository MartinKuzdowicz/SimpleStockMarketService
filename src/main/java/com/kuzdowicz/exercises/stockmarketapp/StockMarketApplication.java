package com.kuzdowicz.exercises.stockmarketapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.kuzdowicz.exercises.stockmarketapp.config.AppConfig;
import com.kuzdowicz.exercises.stockmarketapp.services.ExchangeAdministrator;
import com.kuzdowicz.exercises.stockmarketapp.services.StockService;
import com.kuzdowicz.exercises.stockmarketapp.services.TradingService;

public class StockMarketApplication {

	private static ApplicationContext application;

	public static void main(String[] args) {

		application = new AnnotationConfigApplicationContext(AppConfig.class);

		ExchangeAdministrator admin = application.getBean(ExchangeAdministrator.class);

		admin.addCommonStockToMarket("TEA", "0.0189", "36590", "0.006");
		
		StockService stockService = application.getBean(StockService.class);
		
		TradingService tradingService = application.getBean(TradingService.class);

		System.out.println("+--------+----------------+-----------+-------------+");
		System.out.println("| Symbol | Dividend yield | P/E ratio | Stock price |");
		System.out.println("+--------+----------------+-----------+-------------+");
		
		stockService.printCurrentStockData();
		

		

	}

}
