package tests;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.kuzdowicz.exercises.stockmarketapp.domain.Stock;
import com.kuzdowicz.exercises.stockmarketapp.factories.StockFactory;
import com.kuzdowicz.exercises.stockmarketapp.helpers.FinancialMathCalculator;
import com.kuzdowicz.exercises.stockmarketapp.helpers.StockDataCalculator;

public class StockDataCalculatorTest {

	private StockDataCalculator sdc;
	private StockFactory sf;

	@Before
	public void prepare() {
		sdc = new StockDataCalculator(new FinancialMathCalculator());
		sf = new StockFactory();
	}

	@Test
	public void calculateLastDividendForGivenCommonStockTest() {

		Stock stock = sf.createCommonStock("TEST", "70", "95", "10", "0.05");

		BigDecimal tickerPrice = new BigDecimal("100");

		BigDecimal expected = new BigDecimal("5.00");

		BigDecimal actual = sdc.calculateLastDividendFor(stock, tickerPrice);

		assertEquals(expected, actual);
	}

	@Test
	public void calculateLastDividendForGivenPreferredStockTest() {

		Stock stock = sf.createPreferredStock("TEST", "70", "95", "10", "0.01");

		BigDecimal tickerPrice = new BigDecimal("100");

		BigDecimal expected = new BigDecimal("0.70");

		BigDecimal actual = sdc.calculateLastDividendFor(stock, tickerPrice);

		assertEquals(expected, actual);
	}

	@Test
	public void calculateDividendYieldForGivenCommonStockTest() {

		Stock stock = sf.createCommonStock("TEST", "70", "95", "10", "0.01");

		BigDecimal tickerPrice = new BigDecimal("100");
		BigDecimal lastDividendVal = new BigDecimal("15.05");

		BigDecimal expected = new BigDecimal("0.1505");

		BigDecimal actual = sdc.calculateDividendYieldFor(stock, tickerPrice, lastDividendVal);

		assertEquals(expected, actual);
	}

	@Test
	public void calculateDividendYieldForGivenPreferredStockTest() {

		Stock stock = sf.createPreferredStock("TEST", "70", "95", "10", "0.01");
		BigDecimal tickerPrice = new BigDecimal("150");
		BigDecimal lastDividendVal = new BigDecimal("2");

		BigDecimal expected = new BigDecimal("0.004667");

		BigDecimal actual = sdc.calculateDividendYieldFor(stock, tickerPrice, lastDividendVal);

		assertEquals(expected, actual);
	}

}
