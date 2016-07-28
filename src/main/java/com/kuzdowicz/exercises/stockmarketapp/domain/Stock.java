package com.kuzdowicz.exercises.stockmarketapp.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Stock {

	private final String tickerSymbol;
	private final BigDecimal parValue;
	private final BigInteger nrOfSharesInIssue;
	private final Dividend dividend;

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

}
