package com.kuzdowicz.exercises.stockmarketapp.domain;

import java.math.BigDecimal;

import com.kuzdowicz.exercises.stockmarketapp.constants.SecurityType;

public class Dividend {

	private final SecurityType type;
	private BigDecimal dividendRate;

	public Dividend(SecurityType type, BigDecimal dividendRate) {
		this.type = type;
		this.dividendRate = dividendRate;
	}

	public BigDecimal getDividendRate() {
		return dividendRate;
	}

	public void setDividendRate(BigDecimal dividendRate) {
		this.dividendRate = dividendRate;
	}

	public SecurityType getType() {
		return type;
	}

}
