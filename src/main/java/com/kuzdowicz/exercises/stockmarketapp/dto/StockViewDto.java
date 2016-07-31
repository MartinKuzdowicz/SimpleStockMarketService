package com.kuzdowicz.exercises.stockmarketapp.dto;

import java.math.BigDecimal;

import com.kuzdowicz.exercises.stockmarketapp.constants.SecurityType;

public class StockViewDto {

	private String ticker;
	private SecurityType type;
	private BigDecimal stockPrice;
	private BigDecimal lastDividend;
	private BigDecimal fixedDividend;
	private BigDecimal dividendYield;
	private BigDecimal parValue;
	private BigDecimal PERatio;

	public void setScaleInBigDecimalFieldsForPresentation() {

		this.stockPrice = this.stockPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
		if (lastDividend != null) {
			this.lastDividend = this.lastDividend.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		if (fixedDividend != null) {
			this.fixedDividend = this.fixedDividend.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		if (dividendYield != null) {
			this.dividendYield = this.dividendYield.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		if (parValue != null) {
			this.parValue = this.parValue.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.PERatio = this.PERatio.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public SecurityType getType() {
		return type;
	}

	public void setType(SecurityType type) {
		this.type = type;
	}

	public BigDecimal getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(BigDecimal stockPrice) {
		this.stockPrice = stockPrice;
	}

	public BigDecimal getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(BigDecimal lastDividend) {
		this.lastDividend = lastDividend;
	}

	public BigDecimal getFixedDividend() {
		return fixedDividend;
	}

	public void setFixedDividend(BigDecimal fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	public BigDecimal getDividendYield() {
		return dividendYield;
	}

	public void setDividendYield(BigDecimal dividendYield) {
		this.dividendYield = dividendYield;
	}

	public BigDecimal getParValue() {
		return parValue;
	}

	public void setParValue(BigDecimal parValue) {
		this.parValue = parValue;
	}

	public BigDecimal getPERatio() {
		return PERatio;
	}

	public void setPERatio(BigDecimal pERatio) {
		PERatio = pERatio;
	}

}
