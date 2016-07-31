package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.kuzdowicz.exercises.stockmarketapp.constants.Indicator;
import com.kuzdowicz.exercises.stockmarketapp.domain.Trade;
import com.kuzdowicz.exercises.stockmarketapp.repositories.StocksRepository;
import com.kuzdowicz.exercises.stockmarketapp.repositories.TradesRepository;
import com.kuzdowicz.exercises.stockmarketapp.services.StocksExchangeAdministrator;
import com.kuzdowicz.exercises.stockmarketapp.services.StocksService;
import com.kuzdowicz.exercises.stockmarketapp.services.TradingService;

import tests.config.AppConfigTest;

public class StocksServiceTest {

	private ApplicationContext application;

	private StocksService stocksService;

	private StocksRepository stocksRepository;

	private TradingService tradingService;

	private TradesRepository tradesRepository;

	@Before
	public void prepare() {

		application = new AnnotationConfigApplicationContext(AppConfigTest.class);

		StocksExchangeAdministrator admin = application.getBean(StocksExchangeAdministrator.class);

		admin.addCommonStockToMarket("TEA", "0.0189", "1.78", "36590", "0.01");
		admin.addCommonStockToMarket("POP", "0.347", "2.58", "207", "0.04");
		admin.addPreferredStockToMarket("GIN", "8", "4", "10", "0.02");

		stocksService = application.getBean(StocksService.class);
		stocksRepository = application.getBean(StocksRepository.class);
		tradingService = application.getBean(TradingService.class);
		tradesRepository = application.getBean(TradesRepository.class);

	}

	@Test
	public void tickerSymbolsAreNotNull() {

		Set<String> tickerSymbols = stocksService.getTickerSymbols();

		assertNotNull(tickerSymbols);

	}

	public void calculateTickerPriceOnOpeningPriceIfNoTradesPresent() {

		String ticker = "TEA";

		BigDecimal openingPriceForTEA = stocksRepository.findOne(ticker).getOpeningPrice();

		BigDecimal currentTicker = stocksService.calculateTickerPriceFor(ticker);

		assertEquals(openingPriceForTEA, currentTicker);

	}

	@Test
	public void actualTickerPriceIsNotEqualToOpeningPriceIfTradesHaveDifferanteValuesThenOpeningPrice() {

		String ticker = "TEA";

		tradingService.buyStock(ticker, new BigDecimal("100"), new BigInteger("5"));
		tradingService.buyStock(ticker, new BigDecimal("120"), new BigInteger("7"));

		BigDecimal openingPriceForTEA = stocksRepository.findOne(ticker).getOpeningPrice();
		BigDecimal tickerPrice = stocksService.calculateTickerPriceFor(ticker);

		assertNotEquals(openingPriceForTEA, tickerPrice);

	}

	@Test
	public void caclulateTickerPriceOnTradesFromPast15MinTest1() {

		String ticker = "TEA";

		tradingService.buyStock(ticker, new BigDecimal("100"), new BigInteger("5"));
		tradingService.buyStock(ticker, new BigDecimal("120"), new BigInteger("7"));

		BigDecimal currentTickerPrice = stocksService.calculateTickerPriceFor(ticker).setScale(2,
				BigDecimal.ROUND_HALF_UP);

		BigDecimal expectedTickerPrice = new BigDecimal("111.70").setScale(2, BigDecimal.ROUND_HALF_UP);

		assertEquals(currentTickerPrice, expectedTickerPrice);

	}

	@Test
	public void caclulateTickerPriceOnTradesFromPast15MinTest2() {

		DateTime time10MinAgo = DateTime.now().minusMinutes(10);

		String ticker = "TEA";

		Trade t1 = new Trade(ticker, new BigDecimal("80"), new BigInteger("15"), Indicator.SELL, time10MinAgo);
		tradesRepository.recordTrade(t1);

		tradingService.buyStock(ticker, new BigDecimal("100"), new BigInteger("5"));
		tradingService.buyStock(ticker, new BigDecimal("120"), new BigInteger("7"));

		BigDecimal currentTickerPrice = stocksService.calculateTickerPriceFor(ticker).setScale(2,
				BigDecimal.ROUND_HALF_UP);

		BigDecimal expectedTickerPrice = new BigDecimal("94.07").setScale(2, BigDecimal.ROUND_HALF_UP);

		assertEquals(currentTickerPrice, expectedTickerPrice);

	}

	@Test
	public void caclulateTickerPriceOnTradesFromPast15MinTest3() {

		DateTime time35MinAgo = DateTime.now().minusMinutes(35);

		DateTime time10MinAgo = DateTime.now().minusMinutes(10);

		DateTime time12MinAgo = DateTime.now().minusMinutes(12);

		DateTime time5MinAgo = DateTime.now().minusMinutes(12);

		String ticker = "TEA";

		Trade t1 = new Trade(ticker, new BigDecimal("80"), new BigInteger("5"), Indicator.SELL, time35MinAgo);
		
		Trade t2 = new Trade(ticker, new BigDecimal("70"), new BigInteger("10"), Indicator.BUY, time12MinAgo);
		Trade t3 = new Trade(ticker, new BigDecimal("70"), new BigInteger("2"), Indicator.SELL, time5MinAgo);
		Trade t4 = new Trade(ticker, new BigDecimal("50"), new BigInteger("20"), Indicator.BUY, time10MinAgo);

		tradesRepository.recordTrade(t1);
		tradesRepository.recordTrade(t2);
		tradesRepository.recordTrade(t3);
		tradesRepository.recordTrade(t4);

		tradingService.buyStock(ticker, new BigDecimal("100"), new BigInteger("5"));
		tradingService.buyStock(ticker, new BigDecimal("120"), new BigInteger("7"));

		BigDecimal currentTickerPrice = stocksService.calculateTickerPriceFor(ticker).setScale(2,
				BigDecimal.ROUND_HALF_UP);

		BigDecimal expectedTickerPrice = new BigDecimal("72.27").setScale(2, BigDecimal.ROUND_HALF_UP);

		assertEquals(currentTickerPrice, expectedTickerPrice);

	}

	@Test
	public void caclulateTickerPriceOnTradesFromPast15MinAndIgnoreTradesFromPast30Minutes1() {

		DateTime time30MinAgo = DateTime.now().minusMinutes(30);

		String ticker = "TEA";

		Trade t1 = new Trade(ticker, new BigDecimal("80"), new BigInteger("15"), Indicator.BUY, time30MinAgo);
		tradesRepository.recordTrade(t1);

		tradingService.buyStock(ticker, new BigDecimal("100"), new BigInteger("5"));
		tradingService.buyStock(ticker, new BigDecimal("120"), new BigInteger("7"));

		BigDecimal currentTickerPrice = stocksService.calculateTickerPriceFor(ticker).setScale(2,
				BigDecimal.ROUND_HALF_UP);

		BigDecimal expectedTickerPrice = new BigDecimal("111.70").setScale(2, BigDecimal.ROUND_HALF_UP);

		assertEquals(currentTickerPrice, expectedTickerPrice);

	}

	@Test
	public void caclulateTickerPriceOnTradesFromPast15MinAndIgnoreTradesFromPast30Minutes2() {

		DateTime time30MinAgo = DateTime.now().minusMinutes(16);

		String ticker = "TEA";

		Trade t1 = new Trade(ticker, new BigDecimal("80"), new BigInteger("15"), Indicator.SELL, time30MinAgo);
		tradesRepository.recordTrade(t1);

		tradingService.buyStock(ticker, new BigDecimal("100"), new BigInteger("5"));
		tradingService.buyStock(ticker, new BigDecimal("120"), new BigInteger("7"));

		BigDecimal currentTickerPrice = stocksService.calculateTickerPriceFor(ticker).setScale(2,
				BigDecimal.ROUND_HALF_UP);

		BigDecimal expectedTickerPrice = new BigDecimal("111.70").setScale(2, BigDecimal.ROUND_HALF_UP);

		assertEquals(currentTickerPrice, expectedTickerPrice);

	}

}
