package com.kuzdowicz.exercises.stockmarketapp.repositories;

import java.util.List;

import com.kuzdowicz.exercises.stockmarketapp.domain.Stock;
import com.kuzdowicz.exercises.stockmarketapp.domain.Trade;


public interface StocksRepository {

	Stock saveOrUpdate(Stock s);
	
	List<Stock> findAll();
	
	Stock findOne(String id);
	
	void recordTradeForStock(String stockId, Trade trade);

}
