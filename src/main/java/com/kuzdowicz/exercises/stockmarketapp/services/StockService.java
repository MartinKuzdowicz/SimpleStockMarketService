package com.kuzdowicz.exercises.stockmarketapp.services;

import java.math.BigDecimal;

import com.kuzdowicz.exercises.stockmarketapp.domain.Stock;
import com.kuzdowicz.exercises.stockmarketapp.dto.StockViewDto;

public interface StockService {

	BigDecimal calculateLastStockPriceFor(Stock stock, int minutes);

	BigDecimal caclulateDividendYield(Stock stock, BigDecimal tickerPrice, BigDecimal latsDividenVal);

	BigDecimal calculateLastDividend(Stock stock, BigDecimal tickerPrice);

	BigDecimal calculateAllShareIndex();

	StockViewDto assemblyDataToStockQuote(Stock stock);
}
