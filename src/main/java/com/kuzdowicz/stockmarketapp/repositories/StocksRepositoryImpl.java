package com.kuzdowicz.stockmarketapp.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import static com.kuzdowicz.stockmarketapp.constants.StockType.*;
import com.kuzdowicz.stockmarketapp.domain.Stock;

@Repository
public class StocksRepositoryImpl implements StocksRepository {

	private final Map<String, Stock> stocks = new HashMap<>();

	public StocksRepositoryImpl() {

		saveOrUpdate(new Stock("TEA", "TEA Company Co", //
				new BigDecimal("100.89"), COMMON, new BigDecimal("20.67")));

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

}
