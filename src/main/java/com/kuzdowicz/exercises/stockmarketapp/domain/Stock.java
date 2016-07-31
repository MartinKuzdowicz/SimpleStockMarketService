package com.kuzdowicz.exercises.stockmarketapp.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Stock {

	private final String tickerSymbol;
	private final BigDecimal parValue;
	private final BigInteger nrOfOutstandingShares;
	private final Dividend dividend;
	private final BigDecimal openingPrice;

	private BigDecimal lastPrice;

	public Stock(String tickerSymbol, BigDecimal parValue, BigDecimal openingPrice, BigInteger nrOfOutstandingShares,
			Dividend dividend) {
		this.tickerSymbol = tickerSymbol;
		this.parValue = parValue;
		this.nrOfOutstandingShares = nrOfOutstandingShares;
		this.dividend = dividend;
		this.openingPrice = openingPrice;
	}

	public Stock(String tickerSymbol, BigDecimal parValue, BigDecimal openingPrice, BigInteger nrOfOutstandingShares) {
		this.tickerSymbol = tickerSymbol;
		this.parValue = parValue;
		this.nrOfOutstandingShares = nrOfOutstandingShares;
		this.openingPrice = openingPrice;
		dividend = null;
	}

	public String getTickerSymbol() {
		return tickerSymbol;
	}

	public BigDecimal getParValue() {
		return parValue;
	}

	public BigInteger getNrOfOutstandingShares() {
		return nrOfOutstandingShares;
	}

	public Dividend getDividend() {
		return dividend;
	}

	public BigDecimal getOpeningPrice() {
		return openingPrice;
	}

	public BigDecimal getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(BigDecimal lastPrice) {
		this.lastPrice = lastPrice;
	}

}
