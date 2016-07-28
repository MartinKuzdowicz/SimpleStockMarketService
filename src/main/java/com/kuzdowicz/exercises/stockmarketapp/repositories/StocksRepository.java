package com.kuzdowicz.exercises.stockmarketapp.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import com.kuzdowicz.exercises.stockmarketapp.domain.Stock;


public interface StocksRepository {

	Stock saveOrUpdate(Stock id);
	
	void updateTickerPrice(String id, BigDecimal price);
	
	List<Stock> findAll();
	
	Set<String> findAllTickerSymbols();
	
	Stock findOne(String id);
	

}
