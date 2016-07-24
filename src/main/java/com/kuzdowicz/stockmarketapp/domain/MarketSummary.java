package com.kuzdowicz.stockmarketapp.domain;

import java.math.BigDecimal;

public class MarketSummary {

	private final String name;

	public MarketSummary(String name) {
		this.name = name;
	}

	private long volume;
	private BigDecimal index;

	public String getName() {
		return name;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	public BigDecimal getIndex() {
		return index;
	}

	public void setIndex(BigDecimal index) {
		this.index = index;
	}

}
