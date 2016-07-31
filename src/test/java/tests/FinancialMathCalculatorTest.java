package tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.kuzdowicz.exercises.stockmarketapp.helpers.FinancialMathCalculator;

public class FinancialMathCalculatorTest {

	private FinancialMathCalculator fmc;

	@Before
	public void prepare() {
		fmc = new FinancialMathCalculator();
	}

	@Test
	public void calculatePERatioTest() {

		BigDecimal price = new BigDecimal("150");
		BigDecimal dividend = new BigDecimal("2");

		BigDecimal expected = new BigDecimal("75");

		BigDecimal actual = fmc.calculatePERatio(price, dividend);

		assertEquals(expected, actual);
	}

	@Test
	public void calculateDividendValTest1() {

		BigDecimal parValue = new BigDecimal("60");
		BigDecimal dividendRate = new BigDecimal("0.1");

		BigDecimal expected = new BigDecimal("6.0");

		BigDecimal actual = fmc.calculateDividendVal(parValue, dividendRate);

		assertEquals(expected, actual);
	}

	@Test
	public void calculateDividendValTest2() {

		BigDecimal tickerPrice = new BigDecimal("100");
		BigDecimal dividendRate = new BigDecimal("0.05");

		BigDecimal expected = new BigDecimal("5.00");

		BigDecimal actual = fmc.calculateDividendVal(tickerPrice, dividendRate);

		assertEquals(expected, actual);

	}
	
	@Test
	public void calculateDividendYieldForCommon() {
		
		BigDecimal tickerPrice = new BigDecimal("100");
		BigDecimal lastDividenVal = new BigDecimal("20.77");

		BigDecimal expected = new BigDecimal("0.2077");

		BigDecimal actual = fmc.calculateDividendYieldForCommon(tickerPrice, lastDividenVal);

		assertEquals(expected, actual);
		
	}
	
	@Test
	public void calculateDividendYieldForPreferred() {
		
		BigDecimal dividendRate = new BigDecimal("0.1");
		BigDecimal parValue = new BigDecimal("100");
		BigDecimal tickerPrice = new BigDecimal("150");


		BigDecimal expected = new BigDecimal("0.06667");

		BigDecimal actual = fmc.calculateDividendYieldForPreferred(tickerPrice, parValue, dividendRate);

		assertEquals(expected, actual);
		
	}

}
