package com.kuzdowicz.exercises.stockmarketapp.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kuzdowicz.exercises.stockmarketapp.database.StockMarketAppDB;
import com.kuzdowicz.exercises.stockmarketapp.domain.Stock;
import com.kuzdowicz.exercises.stockmarketapp.domain.Trade;

@Repository
@Transactional
public class StocksRepositoryImpl implements StocksRepository {

	private final StockMarketAppDB stockMarketAppDB;

	@Autowired
	public StocksRepositoryImpl(StockMarketAppDB stockMarketAppDB) {
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

	@Override
	public void recordTradeForStock(String stockId, Trade trade) {
		stockMarketAppDB.getStocks().get(stockId).getTrades().add(trade);
	}

}
