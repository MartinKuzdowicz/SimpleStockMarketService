package com.kuzdowicz.exercises.stockmarketapp.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Stock {

	private final String tickerSymbol;
	private final BigDecimal parValue;
	private final BigInteger nrOfSharesInIssue;

	private final Dividend dividend;

	private final List<Trade> trades = new ArrayList<>();

	public Stock(String tickerSymbol, BigDecimal parValue, BigInteger nrOfSharesInIssue, Dividend dividend) {
		this.tickerSymbol = tickerSymbol;
		this.parValue = parValue;
		this.nrOfSharesInIssue = nrOfSharesInIssue;
		this.dividend = dividend;
	}

	public Stock(String tickerSymbol, BigDecimal parValue, BigInteger nrOfSharesInIssue) {
		this.tickerSymbol = tickerSymbol;
		this.parValue = parValue;
		this.nrOfSharesInIssue = nrOfSharesInIssue;
		dividend = null;
	}

	private BigDecimal tickerPrice;

	public BigDecimal getTickerPrice() {
		return tickerPrice;
	}

	public void setTickerPrice(BigDecimal tickerPrice) {
		this.tickerPrice = tickerPrice;
	}

	public String getTickerSymbol() {
		return tickerSymbol;
	}

	public BigDecimal getParValue() {
		return parValue;
	}

	public BigInteger getNrOfSharesInIssue() {
		return nrOfSharesInIssue;
	}

	public Dividend getDividend() {
		return dividend;
	}

	public List<Trade> getTrades() {
		return trades;
	}

}
