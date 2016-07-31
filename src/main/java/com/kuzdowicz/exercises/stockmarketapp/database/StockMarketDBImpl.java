package com.kuzdowicz.exercises.stockmarketapp.database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.kuzdowicz.exercises.stockmarketapp.domain.Stock;
import com.kuzdowicz.exercises.stockmarketapp.domain.Trade;

@Component
public class StockMarketDBImpl implements StockMarketDB {

	private final Map<String, Stock> stocks = new HashMap<>();
	private final Map<String, List<Trade>> trades = new HashMap<>();

	public Map<String, Stock> getStocks() {
		return stocks;
	}

	public Map<String, List<Trade>> getTrades() {
		return trades;
	}

}
