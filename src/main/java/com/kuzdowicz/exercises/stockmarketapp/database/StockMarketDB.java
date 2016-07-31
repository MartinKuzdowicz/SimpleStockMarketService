package com.kuzdowicz.exercises.stockmarketapp.database;

import java.util.List;
import java.util.Map;

import com.kuzdowicz.exercises.stockmarketapp.domain.Stock;
import com.kuzdowicz.exercises.stockmarketapp.domain.Trade;

public interface StockMarketDB {
	
	Map<String, Stock> getStocks();
	
	Map<String, List<Trade>> getTrades();

}
