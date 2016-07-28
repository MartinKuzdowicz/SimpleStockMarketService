package com.kuzdowicz.exercises.stockmarketapp.helpers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.List;

import org.springframework.stereotype.Component;

import com.kuzdowicz.exercises.stockmarketapp.domain.Trade;

@Component
public class FinancialMathCalculator {

	public final static int ROUND = BigDecimal.ROUND_HALF_UP;

	public final static MathContext MATH_CONTEXT = new MathContext(BigDecimal.ROUND_HALF_UP);
	

	public BigDecimal calculateDividendYieldForCommon(BigDecimal tickerPrice, BigDecimal latsDividenVal) {
		return latsDividenVal.divide(tickerPrice, ROUND);
	}

	public BigDecimal calculateDividendYieldForPreferred(BigDecimal tickerPrice, BigDecimal parValue,
			BigDecimal dividendRate) {
		return parValue.multiply(dividendRate, MATH_CONTEXT).divide(tickerPrice, ROUND);
	}

	public BigDecimal caclulateCommonDividendPerShareVal(BigDecimal tickerPrice, BigDecimal dividendRate) {
		return tickerPrice.multiply(dividendRate, MATH_CONTEXT);
	}

	public BigDecimal caclulateFixedDividendVal(BigDecimal parValue, BigDecimal dividendRate) {
		return parValue.multiply(dividendRate, MATH_CONTEXT);
	}

	public BigDecimal calculatePERatio(BigDecimal tickerPrice, BigDecimal dividendVal) {
		return tickerPrice.divide(dividendVal, ROUND);
	}

	public BigDecimal geometricMean(List<BigDecimal> stocksLastPrices) {

		BigDecimal tickerPricesMultiplyed = stocksLastPrices.parallelStream().reduce(BigDecimal.ZERO,
				BigDecimal::multiply);

		double geoMean = Math.pow(tickerPricesMultiplyed.doubleValue(), 1.0 / stocksLastPrices.size());

		return new BigDecimal(geoMean);

	}

	public BigDecimal calculateStockPriceFor(List<Trade> trades) {

		BigDecimal sumOfAllPriceTimesQty = trades.parallelStream()//
				.map(t -> t.getPrice().multiply(new BigDecimal(t.getQuantity(), MATH_CONTEXT)))//
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		BigInteger allSharesCountInTrades = trades.stream()//
				.map(t -> t.getQuantity())//
				.reduce(BigInteger.ZERO, BigInteger::add);

		BigDecimal currentStockPrice = sumOfAllPriceTimesQty.divide(new BigDecimal(allSharesCountInTrades), ROUND);

		return currentStockPrice;

	}

}
