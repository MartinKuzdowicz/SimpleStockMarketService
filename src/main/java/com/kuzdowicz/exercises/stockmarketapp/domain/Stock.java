package com.kuzdowicz.exercises.stockmarketapp.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.kuzdowicz.exercises.stockmarketapp.constants.StockType;

public class Stock {

	/* sprawdz czy nie musi byc ilosc !!!!!! */

	private final String tickerSymbol;
	private final String companyName;
	private final BigDecimal parValue;
	private final StockType type;
	private final BigDecimal fixedDividend;

	private final List<Trade> trades = new ArrayList<>();

	public Stock(String tickerSymbol, String companyName, BigDecimal parValue, StockType type,
			BigDecimal fixedDividend) {
		this.tickerSymbol = tickerSymbol;
		this.companyName = companyName;
		this.parValue = parValue;
		this.type = type;
		this.fixedDividend = fixedDividend;
	}

	private BigDecimal lastDividend;
	private BigDecimal tickerPrice;

	public BigDecimal getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(BigDecimal lastDividend) {
		this.lastDividend = lastDividend;
	}

	public String getTickerSymbol() {
		return tickerSymbol;
	}

	public String getCompanyName() {
		return companyName;
	}

	public BigDecimal getParValue() {
		return parValue;
	}

	public StockType getType() {
		return type;
	}

	public BigDecimal getFixedDividend() {
		return fixedDividend;
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
