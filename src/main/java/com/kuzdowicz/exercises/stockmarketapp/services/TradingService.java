package com.kuzdowicz.exercises.stockmarketapp.services;

import java.math.BigDecimal;

public interface TradingService {
	
	BigDecimal getAllShareIndex();
	
	void byStock(String ticker);
	
	void sellStock(String ticker);

}
