package com.kuzdowicz.exercises.stockmarketapp.repositories;

import java.util.List;

import com.kuzdowicz.exercises.stockmarketapp.domain.Stock;


public interface StocksRepository {

	Stock saveOrUpdate(Stock s);
	
	List<Stock> findAll();
	
	Stock findOne(String id);
	

}
