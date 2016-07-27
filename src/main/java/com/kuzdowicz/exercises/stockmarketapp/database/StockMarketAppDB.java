package com.kuzdowicz.exercises.stockmarketapp.database;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kuzdowicz.exercises.stockmarketapp.domain.Stock;
import com.kuzdowicz.exercises.stockmarketapp.domain.Trade;
import com.kuzdowicz.exercises.stockmarketapp.factories.StockFactory;

@Component
public class StockMarketAppDB {

	private final StockFactory stockFactory;

	@Autowired
	public StockMarketAppDB(StockFactory stockFactory) {
		this.stockFactory = stockFactory;
	}

	private final Map<String, Stock> stocks = new HashMap<>();
	private final Map<String, HashMap<Integer, Trade>> trades = new HashMap<>();

	public Map<String, Stock> getStocks() {
		return stocks;
	}

	public Map<String, HashMap<Integer, Trade>> getTrades() {
		return trades;
	}

	public void loadStocksToDB() {

		stocks.put("TEA", stockFactory.createCommonStock("TEA", "100.89", "365900", "0.006"));

	}

}
