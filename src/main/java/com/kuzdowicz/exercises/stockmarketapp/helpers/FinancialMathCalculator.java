package com.kuzdowicz.exercises.stockmarketapp.helpers;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class FinancialMathCalculator {

	public final static MathContext MATH_CONTEXT = new MathContext(BigDecimal.ROUND_HALF_UP);

	public BigDecimal calculateDividendYieldForCommon(BigDecimal tickerPrice, BigDecimal latsDividenVal) {
		return latsDividenVal.divide(tickerPrice, MATH_CONTEXT);
	}

	public BigDecimal calculateDividendYieldForPreferred(BigDecimal tickerPrice, BigDecimal parValue,
			BigDecimal dividendRate) {
		return parValue.multiply(dividendRate, MATH_CONTEXT).divide(tickerPrice, MATH_CONTEXT);
	}

	public BigDecimal caclulateCommonDividendPerShareVal(BigDecimal tickerPrice, BigDecimal dividendRate) {
		return tickerPrice.multiply(dividendRate, MATH_CONTEXT);
	}

	public BigDecimal caclulateFixedDividendVal(BigDecimal parValue, BigDecimal dividendRate) {
		return parValue.multiply(dividendRate, MATH_CONTEXT);
	}

	public BigDecimal calculatePERatio(BigDecimal tickerPrice, BigDecimal dividendVal) {
		return tickerPrice.divide(dividendVal, MATH_CONTEXT);
	}

	public BigDecimal geometricMean(List<BigDecimal> stocksLastPrices) {

		BigDecimal tickerPricesMultiplied = stocksLastPrices.parallelStream().reduce(BigDecimal.ONE,
				BigDecimal::multiply);

		double geoMean = Math.pow(tickerPricesMultiplied.doubleValue(), 1.0 / stocksLastPrices.size());

		return new BigDecimal(geoMean);

	}

}
