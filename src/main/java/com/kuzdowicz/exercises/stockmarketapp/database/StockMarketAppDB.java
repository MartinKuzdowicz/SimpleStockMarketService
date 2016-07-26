package com.kuzdowicz.exercises.stockmarketapp.database;

import static com.kuzdowicz.exercises.stockmarketapp.constants.StockType.COMMON;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.kuzdowicz.exercises.stockmarketapp.domain.Stock;

@Component
public class StockMarketAppDB {

	private final Map<String, Stock> stocks = new HashMap<>();

	public Map<String, Stock> getStocks() {
		return stocks;
	}

	public void loadStocksToDB() {

		stocks.put("TEA", new Stock("TEA", new BigDecimal("100.89"), COMMON, 2));

	}

}
