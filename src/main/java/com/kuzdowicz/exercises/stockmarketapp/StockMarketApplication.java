package com.kuzdowicz.exercises.stockmarketapp;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.kuzdowicz.exercises.stockmarketapp.config.AppConfig;
import com.kuzdowicz.exercises.stockmarketapp.services.ExchangeAdministrator;
import com.kuzdowicz.exercises.stockmarketapp.services.TradingService;

public class StockMarketApplication {

	private static ApplicationContext application;

	public static void main(String[] args) {

		application = new AnnotationConfigApplicationContext(AppConfig.class);

		ExchangeAdministrator admin = application.getBean(ExchangeAdministrator.class);

		admin.addCommonStockToMarket("TEA", "0.0189", "1.78", "36590", "0.01");
		admin.addCommonStockToMarket("POP", "0.347", "2.58", "207", "0.04");
		admin.addPreferredStockToMarket("GIN", "8", "4", "10", "0.02");

		TradingService tradingService = application.getBean(TradingService.class);

		System.out.println();
		System.out.println("Global Beverage Corporation Exchange");
		System.out.println();

		System.out.println(
				"+--------+------+---------------+----------------+----------------+-----------+-----------+-------------+");
		System.out.println(
				"| Symbol | type | last Dividend | fixed Dividend | Dividend yield | par Value | P/E ratio | Stock price |");
		System.out.println(
				"+--------+------+---------------+----------------+----------------+-----------+-----------+-------------+");
		System.out.println();

		admin.printCurrentStockData();

		tradingService.byStock("TEA", new BigDecimal("30.67"), new BigInteger("10"));

		tradingService.byStock("TEA", new BigDecimal("50.67"), new BigInteger("3"));

		admin.printCurrentStockData();

		System.out.println();
		System.out.println("Global Beverage Corporation Exchange - INDEX: ");
		System.out.println();

		System.out.println(admin.getAllShareIndex());

	}

}
