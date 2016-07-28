package com.kuzdowicz.exercises.stockmarketapp.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Stock {

	private final String tickerSymbol;
	private final BigDecimal parValue;
	private final BigInteger nrOfSharesInIssue;
	private final Dividend dividend;

	private BigDecimal lastPrice;

	public Stock(String tickerSymbol, BigDecimal parValue, BigDecimal lastPrice, BigInteger nrOfSharesInIssue,
			Dividend dividend) {
		this.tickerSymbol = tickerSymbol;
		this.parValue = parValue;
		this.nrOfSharesInIssue = nrOfSharesInIssue;
		this.dividend = dividend;
		this.lastPrice = lastPrice;
	}

	public Stock(String tickerSymbol, BigDecimal parValue, BigDecimal lastPrice, BigInteger nrOfSharesInIssue) {
		this.tickerSymbol = tickerSymbol;
		this.parValue = parValue;
		this.nrOfSharesInIssue = nrOfSharesInIssue;
		this.lastPrice = lastPrice;
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

	public BigDecimal getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(BigDecimal lastPrice) {
		this.lastPrice = lastPrice;
	}

	@Override
	public String toString() {
		return "Stock [tickerSymbol=" + tickerSymbol + ", parValue=" + parValue + ", nrOfSharesInIssue="
				+ nrOfSharesInIssue + ", dividend=" + dividend + ", lastPrice=" + lastPrice + "]";
	}
	

}
