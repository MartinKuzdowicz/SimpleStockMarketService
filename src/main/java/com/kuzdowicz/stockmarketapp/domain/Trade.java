package com.kuzdowicz.stockmarketapp.domain;

import java.math.BigDecimal;

import org.joda.time.DateTime;

import com.kuzdowicz.stockmarketapp.constants.Indicator;

public class Trade {

	private final BigDecimal price;
	private final long quantity;
	private final Indicator indicator;
	private final DateTime timestamp;

	public Trade(BigDecimal price, long quantity, Indicator indicator, DateTime timestamp) {
		this.price = price;
		this.quantity = quantity;
		this.indicator = indicator;
		this.timestamp = timestamp;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public long getQuantity() {
		return quantity;
	}

	public Indicator getIndicator() {
		return indicator;
	}

	public DateTime getTimestamp() {
		return timestamp;
	}
	
	

}
