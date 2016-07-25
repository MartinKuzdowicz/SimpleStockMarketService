package com.kuzdowicz.exercises.stockmarketapp.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kuzdowicz.exercises.stockmarketapp.domain.Stock;
import com.kuzdowicz.exercises.stockmarketapp.domain.Trade;

import static com.kuzdowicz.exercises.stockmarketapp.constants.StockType.*;

@Repository
@Transactional
public class StocksRepositoryImpl implements StocksRepository {

	private final Map<String, Stock> stocks = new HashMap<>();

	public StocksRepositoryImpl() {

		loadStocks();

	}

	private void loadStocks() {

		saveOrUpdate(new Stock("TEA", new BigDecimal("100.89"), COMMON, new BigDecimal("20.67")));

	}

	@Override
	public Stock saveOrUpdate(Stock s) {
		String tickerSymbol = s.getTickerSymbol();
		stocks.put(tickerSymbol, s);
		return s;
	}

	@Override
	public List<Stock> findAll() {
		return new ArrayList<>(stocks.values());
	}

	@Override
	public Stock findOne(String id) {
		return stocks.get(id);
	}

	@Override
	public void recordTradeForStock(String stockId, Trade trade) {
		stocks.get(stockId).getTrades().add(trade);
	}

}
