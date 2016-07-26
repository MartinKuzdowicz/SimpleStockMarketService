package com.kuzdowicz.exercises.stockmarketapp.domain;

import java.math.BigDecimal;

import com.kuzdowicz.exercises.stockmarketapp.constants.SecurityType;

public class Dividend {

	private final SecurityType type;
	private final BigDecimal fixedDividendRate;
	private BigDecimal lastDividendRate;

	public Dividend(SecurityType type, BigDecimal fixedDividendRate) {
		this.type = type;
		this.fixedDividendRate = fixedDividendRate;
	}

	public SecurityType getType() {
		return type;
	}

	public BigDecimal getFixedDividendRate() {
		return fixedDividendRate;
	}

	public BigDecimal getLastDividendRate() {
		return lastDividendRate;
	}

	public void setLastDividendRate(BigDecimal lastDividendRate) {
		this.lastDividendRate = lastDividendRate;
	}

}
