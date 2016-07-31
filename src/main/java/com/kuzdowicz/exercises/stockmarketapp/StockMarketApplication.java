package com.kuzdowicz.exercises.stockmarketapp;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.kuzdowicz.exercises.stockmarketapp.config.AppConfig;
import com.kuzdowicz.exercises.stockmarketapp.services.StocksExchangeAdministrator;
import com.kuzdowicz.exercises.stockmarketapp.services.TradingService;

public class StockMarketApplication {

	private static ApplicationContext application;

	public static void main(String[] args) {

		application = new AnnotationConfigApplicationContext(AppConfig.class);

		StocksExchangeAdministrator admin = application.getBean(StocksExchangeAdministrator.class);

		admin.addCommonStockToMarket("TEA", "0.018", "1.78", "36590", "0.01");
		admin.addCommonStockToMarket("POP", "0.34", "2.58", "207", "0.04");
		admin.addCommonStockToMarket("ALE", "1.87", "2.00", "10", "0.1");
		admin.addPreferredStockToMarket("GIN", "8.09", "10.78", "10", "0.02");
		admin.addCommonStockToMarket("JOE", "20.87", "35.99", "26", "0.04");

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

		admin.printCurrentStockData();
		System.out.println("----------------------------------------------------------------------------------------");

		tradingService.sellStock("POP", new BigDecimal("3.67"), new BigInteger("500"));
		tradingService.buyStock("TEA", new BigDecimal("30.67"), new BigInteger("10"));
		tradingService.buyStock("TEA", new BigDecimal("50.67"), new BigInteger("3"));
		tradingService.sellStock("ALE", new BigDecimal("15.67"), new BigInteger("100"));
		tradingService.buyStock("JOE", new BigDecimal("20.67"), new BigInteger("69"));
		tradingService.sellStock("GIN", new BigDecimal("12.67"), new BigInteger("55"));
		System.out.println();

		admin.printCurrentStockData();

		System.out.println();
		System.out.println("Global Beverage Corporation Exchange - GBCE INDEX: " + admin.getAllShareIndex());

	}

}
