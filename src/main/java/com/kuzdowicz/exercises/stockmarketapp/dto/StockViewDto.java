package com.kuzdowicz.exercises.stockmarketapp.dto;

public class StockViewDto {

	private String ticker;
	private String type;
	private String stockPrice;
	private String lastDividend;
	private String fixedDividend;
	private String dividendYield;
	private String parValue;
	private String PERatio;

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(String lastDividend) {
		this.lastDividend = lastDividend;
	}

	public String getFixedDividend() {
		return fixedDividend;
	}

	public void setFixedDividend(String fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	public String getDividendYield() {
		return dividendYield;
	}

	public void setDividendYield(String dividendYield) {
		this.dividendYield = dividendYield;
	}

	public String getParValue() {
		return parValue;
	}

	public void setParValue(String parValue) {
		this.parValue = parValue;
	}

	public String getPERatio() {
		return PERatio;
	}

	public void setPERatio(String pERatio) {
		PERatio = pERatio;
	}

	public String getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(String stockPrice) {
		this.stockPrice = stockPrice;
	}

}
