package com.kuzdowicz.exercises.stockmarketapp.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.kuzdowicz.exercises.stockmarketapp.constants.StockType;

public class Stock {

	private final String tickerSymbol;
	private final BigDecimal parValue;
	private final StockType type;
	private final Integer fixedDividendRate;

	private final List<Trade> trades = new ArrayList<>();

	public Stock(String tickerSymbol, BigDecimal parValue, StockType type, Integer fixedDividendRate) {
		this.tickerSymbol = tickerSymbol;
		this.parValue = parValue;
		this.type = type;
		this.fixedDividendRate = fixedDividendRate;
	}

	private Integer lastDividendRate;
	private BigDecimal tickerPrice;

	public Integer getLastDividendRate() {
		return lastDividendRate;
	}

	public void setLastDividend(Integer lastDividendRate) {
		this.lastDividendRate = lastDividendRate;
	}

	public String getTickerSymbol() {
		return tickerSymbol;
	}

	public BigDecimal getParValue() {
		return parValue;
	}

	public StockType getType() {
		return type;
	}

	public Integer getFixedDividendRate() {
		return fixedDividendRate;
	}

	public List<Trade> getTrades() {
		return trades;
	}

	public BigDecimal getTickerPrice() {
		return tickerPrice;
	}

	public void setTickerPrice(BigDecimal tickerPrice) {
		this.tickerPrice = tickerPrice;
	}

}
