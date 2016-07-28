package com.kuzdowicz.exercises.stockmarketapp.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kuzdowicz.exercises.stockmarketapp.config.AppConfig;
import com.kuzdowicz.exercises.stockmarketapp.constants.SecurityType;
import com.kuzdowicz.exercises.stockmarketapp.domain.Dividend;
import com.kuzdowicz.exercises.stockmarketapp.domain.Stock;
import com.kuzdowicz.exercises.stockmarketapp.domain.Trade;
import com.kuzdowicz.exercises.stockmarketapp.dto.StockViewDto;
import com.kuzdowicz.exercises.stockmarketapp.helpers.FinancialMathCalculator;
import com.kuzdowicz.exercises.stockmarketapp.repositories.StocksRepository;
import com.kuzdowicz.exercises.stockmarketapp.repositories.TradesRepository;

@Service
@Transactional
public class StockServiceImpl implements StockService {

	private final StocksRepository stocksRepository;

	private final TradesRepository tradesRepository;

	private final FinancialMathCalculator financialMathCalculator;

	@Autowired
	public StockServiceImpl(StocksRepository stocksRepository, TradesRepository tradesRepository,
			FinancialMathCalculator financialMathCalculator) {
		this.stocksRepository = stocksRepository;
		this.tradesRepository = tradesRepository;
		this.financialMathCalculator = financialMathCalculator;
	}

	@Override
	public BigDecimal calculateLastStockPriceFor(Stock stock, int minutes) {

		String ticker = stock.getTickerSymbol();
		List<Trade> tradesFromLastMinutes = tradesRepository.getTradesByTickerRecordedBefore(ticker, minutes);

		if (tradesFromLastMinutes.isEmpty()) {
			return stock.getLastPrice();
		} else {

			BigDecimal currentStockPrice = financialMathCalculator.calculateStockPriceFor(tradesFromLastMinutes);
			stock.setLastPrice(currentStockPrice);
			stocksRepository.saveOrUpdate(stock);
			return currentStockPrice;
		}

	}

	@Override
	public BigDecimal calculateAllShareIndex() {

		List<BigDecimal> stocksMarketCaps = stocksRepository.findAll().stream()//
				.map(s -> calculateLastStockPriceFor(s, AppConfig.TIME_IN_MIN_INTERVAL_FOR_CACLULATING_LAST_STOCK_PRICE)//
						.multiply(new BigDecimal(s.getNrOfSharesInIssue())))//
				.collect(Collectors.toList());

		return financialMathCalculator.geometricMean(stocksMarketCaps);
	}

	@Override
	public BigDecimal calculateLastDividend(Stock stock, BigDecimal tickerPrice) {

		Dividend dividend = stock.getDividend();

		if (dividend.getType().equals(SecurityType.COMMON)) {

			return financialMathCalculator.caclulateCommonDividendPerShareVal(tickerPrice, dividend.getDividendRate());

		} else {

			return financialMathCalculator.caclulateFixedDividendVal(stock.getParValue(), dividend.getDividendRate());

		}
	}

	public StockViewDto assemblyDataToStockQuote(Stock stock) {

		StockViewDto sqv = new StockViewDto();

		sqv.setTicker(stock.getTickerSymbol());
		sqv.setType(stock.getDividend().getType().name());

		BigDecimal currentTickerPrice = calculateLastStockPriceFor(stock,
				AppConfig.TIME_IN_MIN_INTERVAL_FOR_CACLULATING_LAST_STOCK_PRICE);

		sqv.setStockPrice(currentTickerPrice.toString());

		BigDecimal lastDividend = calculateLastDividend(stock, currentTickerPrice);
		sqv.setLastDividend(lastDividend.toString());

		if (stock.getDividend().getType().equals(SecurityType.PREFFERED)) {
			sqv.setFixedDividend(stock.getDividend().getDividendRate().toString());
		} else {
			sqv.setFixedDividend(" ");
		}

		BigDecimal dividendYield = caclulateDividendYield(stock, currentTickerPrice, lastDividend);
		sqv.setDividendYield(dividendYield.toString());

		sqv.setParValue(stock.getParValue().toString());
		
		BigDecimal peRatio = financialMathCalculator.calculatePERatio(currentTickerPrice, lastDividend);
		sqv.setPERatio(peRatio.toString());

		return sqv;

	}

	@Override
	public BigDecimal caclulateDividendYield(Stock stock, BigDecimal tickerPrice, BigDecimal latsDividenVal) {

		Dividend dividend = stock.getDividend();

		if (dividend.getType().equals(SecurityType.COMMON)) {

			return financialMathCalculator.calculateDividendYieldForCommon(tickerPrice, latsDividenVal);

		} else {

			return financialMathCalculator.calculateDividendYieldForPreferred(tickerPrice, stock.getParValue(),
					dividend.getDividendRate());
		}
	}

}
