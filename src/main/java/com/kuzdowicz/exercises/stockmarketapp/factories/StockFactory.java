package com.kuzdowicz.exercises.stockmarketapp.factories;

import static com.kuzdowicz.exercises.stockmarketapp.constants.SecurityType.COMMON;
import static com.kuzdowicz.exercises.stockmarketapp.constants.SecurityType.PREFFERED;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.stereotype.Component;

import com.kuzdowicz.exercises.stockmarketapp.domain.Dividend;
import com.kuzdowicz.exercises.stockmarketapp.domain.Stock;

@Component
public class StockFactory {

	public Stock createCommonStock(String ticker, String parVal, String lastVal, String qty, String dividendRate) {
		return new Stock(ticker, new BigDecimal(parVal), new BigDecimal(lastVal), new BigInteger(qty),
				new Dividend(COMMON, new BigDecimal(dividendRate)));
	}

	public Stock createPreferredStock(String ticker, String parVal, String lastVal, String qty, String dividendRate) {
		return new Stock(ticker, new BigDecimal(parVal), new BigDecimal(lastVal), new BigInteger(qty),
				new Dividend(PREFFERED, new BigDecimal(dividendRate)));
	}
}
