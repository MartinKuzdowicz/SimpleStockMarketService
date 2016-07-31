package com.kuzdowicz.exercises.stockmarketapp.services;

import java.math.BigDecimal;

import com.kuzdowicz.exercises.stockmarketapp.dto.StockViewDto;

public interface StockDataService {

	BigDecimal calculateTickerPriceFor(String ticker, int minutes);

	BigDecimal caclulateDividendYieldFor(String ticker, BigDecimal tickerPrice, BigDecimal latsDividenVal);

	BigDecimal calculateLastDividendFor(String ticker, BigDecimal tickerPrice);

	StockViewDto assemblyAllCalculationsToStockQuoteFor(String ticker);

	BigDecimal calculateAllShareIndex();
}
