package com.kuzdowicz.exercises.stockmarketapp.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.joda.time.DateTime;

import com.kuzdowicz.exercises.stockmarketapp.constants.Indicator;

public class Trade {

	private final BigDecimal price;
	private final BigInteger quantity;
	private final Indicator indicator;
	private final DateTime timestamp;

	public Trade(BigDecimal price, BigInteger quantity, Indicator indicator, DateTime timestamp) {
		this.price = price;
		this.quantity = quantity;
		this.indicator = indicator;
		this.timestamp = timestamp;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigInteger getQuantity() {
		return quantity;
	}

	public Indicator getIndicator() {
		return indicator;
	}

	public DateTime getTimestamp() {
		return timestamp;
	}

}
