package com.kuzdowicz.stockmarketapp.services;

import java.util.List;

import com.kuzdowicz.stockmarketapp.domain.Stock;
import com.kuzdowicz.stockmarketapp.domain.Trade;

public interface StockMarketTradingService {
	
	void recordTradeForStock(Stock stock, Trade trade);
	
	List<Stock> getAllStocks();

}
