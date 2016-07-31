package com.kuzdowicz.exercises.stockmarketapp.helpers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kuzdowicz.exercises.stockmarketapp.constants.SecurityType;
import com.kuzdowicz.exercises.stockmarketapp.domain.Dividend;
import com.kuzdowicz.exercises.stockmarketapp.domain.Stock;
import com.kuzdowicz.exercises.stockmarketapp.domain.Trade;

@Component
public class StockDataCalculator {

	private final FinancialMathCalculator financialMathCalculator;

	@Autowired
	public StockDataCalculator(FinancialMathCalculator financialMathCalculator) {
		this.financialMathCalculator = financialMathCalculator;
	}

	public BigDecimal calculateLastDividendFor(Stock stock, BigDecimal tickerPrice) {

		Dividend dividend = stock.getDividend();

		if (dividend.getType().equals(SecurityType.COMMON)) {

			return financialMathCalculator.caclulateCommonDividendPerShareVal(tickerPrice, dividend.getDividendRate());

		} else {

			return financialMathCalculator.caclulateFixedDividendVal(stock.getParValue(), dividend.getDividendRate());

		}
	}

	public BigDecimal caclulateDividendYieldFor(Stock stock, BigDecimal tickerPrice, BigDecimal lastDividenVal) {

		Dividend dividend = stock.getDividend();

		if (dividend.getType().equals(SecurityType.COMMON)) {

			return financialMathCalculator.calculateDividendYieldForCommon(tickerPrice, lastDividenVal);

		} else {

			return financialMathCalculator.calculateDividendYieldForPreferred(tickerPrice, stock.getParValue(),
					dividend.getDividendRate());
		}
	}

	public BigDecimal calculateStockPriceFromTrades(List<Trade> trades) {

		BigDecimal sumOfAllPriceTimesQty = trades.parallelStream()//
				.map(t -> t.getPrice().multiply(new BigDecimal(t.getQuantity(), FinancialMathCalculator.MATH_CONTEXT)))//
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		BigInteger allSharesCountInTrades = trades.stream()//
				.map(t -> t.getQuantity())//
				.reduce(BigInteger.ZERO, BigInteger::add);

		BigDecimal currentStockPrice = sumOfAllPriceTimesQty.divide(new BigDecimal(allSharesCountInTrades),
				FinancialMathCalculator.MATH_CONTEXT);

		return currentStockPrice;

	}

	public BigDecimal calculatePERatio(BigDecimal tickerPrice, BigDecimal dividendVal) {
		return financialMathCalculator.calculatePERatio(tickerPrice, dividendVal);
	}

	public BigDecimal calculateAllShareIndexFrom(List<BigDecimal> stocksMarketCaps) {

		return financialMathCalculator.geometricMean(stocksMarketCaps);

	}

}
