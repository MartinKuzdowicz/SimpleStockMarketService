package com.kuzdowicz.exercises.stockmarketapp.database;

import static com.kuzdowicz.exercises.stockmarketapp.constants.SecurityType.COMMON;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.kuzdowicz.exercises.stockmarketapp.domain.Dividend;
import com.kuzdowicz.exercises.stockmarketapp.domain.Stock;
import com.kuzdowicz.exercises.stockmarketapp.domain.Trade;

@Component
public class StockMarketAppDB {

	private final Map<String, Stock> stocks = new HashMap<>();
	private final Map<String, HashMap<Integer, Trade>> trades = new HashMap<>();

	public Map<String, Stock> getStocks() {
		return stocks;
	}

	public Map<String, HashMap<Integer, Trade>> getTrades() {
		return trades;
	}

	public void loadStocksToDB() {
		Dividend TeaDividend = new Dividend(COMMON, new BigDecimal(0.006));
		stocks.put("TEA", new Stock("TEA", new BigDecimal("100.89"), new BigInteger("365900"), TeaDividend));
	}

}
