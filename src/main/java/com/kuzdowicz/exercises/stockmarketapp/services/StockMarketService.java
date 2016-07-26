package com.kuzdowicz.exercises.stockmarketapp.services;

import java.math.BigDecimal;
import java.util.List;

import com.kuzdowicz.exercises.stockmarketapp.domain.Stock;
import com.kuzdowicz.exercises.stockmarketapp.domain.Trade;

public interface StockMarketService {
	
	void recordTradeForStock(String stockId, Trade trade);

	List<Stock> getAllStocks();

	BigDecimal calculateLastStockPriceFor(Stock stock, int minutes);

	Stock findOneStockBy(String symbol);

	BigDecimal calculateAllShareIndex();

}
