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
	public void caclulateFixedDividendValTest() {

		BigDecimal parValue = new BigDecimal("60");
		BigDecimal dividendRate = new BigDecimal("0.1");

		BigDecimal expected = new BigDecimal("6.0");

		BigDecimal actual = fmc.caclulateFixedDividendVal(parValue, dividendRate);

		assertEquals(expected, actual);
	}

}
