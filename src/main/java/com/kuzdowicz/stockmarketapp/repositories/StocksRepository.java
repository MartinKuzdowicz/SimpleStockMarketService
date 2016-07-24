package com.kuzdowicz.stockmarketapp.repositories;

import java.util.List;

import com.kuzdowicz.stockmarketapp.domain.Stock;


public interface StocksRepository {

	Stock saveOrUpdate(Stock s);
	
	List<Stock> findAll();

}
