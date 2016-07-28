package com.kuzdowicz.exercises.stockmarketapp.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kuzdowicz.exercises.stockmarketapp.database.StockMarketDB;
import com.kuzdowicz.exercises.stockmarketapp.domain.Stock;

@Repository
@Transactional
public class StocksRepositoryImpl implements StocksRepository {

	private final StockMarketDB stockMarketAppDB;

	@Autowired
	public StocksRepositoryImpl(StockMarketDB stockMarketAppDB) {
		this.stockMarketAppDB = stockMarketAppDB;
	}

	@Override
	public Stock saveOrUpdate(Stock s) {
		String tickerSymbol = s.getTickerSymbol();
		stockMarketAppDB.getStocks().put(tickerSymbol, s);
		return s;
	}

	@Override
	public List<Stock> findAll() {
		return new ArrayList<>(stockMarketAppDB.getStocks().values());
	}

	@Override
	public Stock findOne(String id) {
		return stockMarketAppDB.getStocks().get(id);
	}

}
