package com.kuzdowicz.exercises.stockmarketapp.services;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface TradingService {

	void byStock(String ticker, BigDecimal price, BigInteger qty);

	void sellStock(String ticker, BigDecimal price, BigInteger qty);

}
