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
public class StockDataServiceImpl implements StockDataService {

	private final StocksRepository stocksRepository;

	private final TradesRepository tradesRepository;

	private final FinancialMathCalculator financialMathCalculator;

	@Autowired
	public StockDataServiceImpl(StocksRepository stocksRepository, TradesRepository tradesRepository,
			FinancialMathCalculator financialMathCalculator) {
		this.stocksRepository = stocksRepository;
		this.tradesRepository = tradesRepository;
		this.financialMathCalculator = financialMathCalculator;
	}

	@Override
	public BigDecimal calculateTickerPriceFor(String ticker, int minutes) {

		List<Trade> tradesFromLastMinutes = tradesRepository.getTradesByTickerRecordedBefore(ticker, minutes);

		if (tradesFromLastMinutes.isEmpty()) {

			if (stocksRepository.findOne(ticker).getLastPrice() != null) {
				return stocksRepository.findOne(ticker).getLastPrice();
			}
			return stocksRepository.findOne(ticker).getOpeningPrice();
		} else {

			BigDecimal currentStockPrice = financialMathCalculator.calculateStockPriceFor(tradesFromLastMinutes);
			stocksRepository.updateTickerPrice(ticker, currentStockPrice);
			return currentStockPrice;
		}

	}

	@Override
	public BigDecimal calculateAllShareIndex() {

		List<BigDecimal> stocksMarketCaps = stocksRepository.findAll().stream()//
				.map(s -> calculateTickerPriceFor(s.getTickerSymbol(),
						AppConfig.TIME_IN_MIN_INTERVAL_FOR_CACLULATING_LAST_STOCK_PRICE)//
								.multiply(new BigDecimal(s.getNrOfOutstandingShares())))//
				.collect(Collectors.toList());

		return financialMathCalculator.geometricMean(stocksMarketCaps);
	}

	@Override
	public BigDecimal calculateLastDividendFor(String ticker, BigDecimal tickerPrice) {

		Stock stock = stocksRepository.findOne(ticker);
		Dividend dividend = stock.getDividend();

		if (dividend.getType().equals(SecurityType.COMMON)) {

			return financialMathCalculator.caclulateCommonDividendPerShareVal(tickerPrice, dividend.getDividendRate());

		} else {

			return financialMathCalculator.caclulateFixedDividendVal(stock.getParValue(), dividend.getDividendRate());

		}
	}

	@Override
	public BigDecimal caclulateDividendYieldFor(String ticker, BigDecimal tickerPrice, BigDecimal lastDividenVal) {

		Stock stock = stocksRepository.findOne(ticker);
		Dividend dividend = stocksRepository.findOne(ticker).getDividend();

		if (dividend.getType().equals(SecurityType.COMMON)) {

			return financialMathCalculator.calculateDividendYieldForCommon(tickerPrice, lastDividenVal);

		} else {

			return financialMathCalculator.calculateDividendYieldForPreferred(tickerPrice, stock.getParValue(),
					dividend.getDividendRate());
		}
	}

	public StockViewDto assemblyAllCalculationsToStockQuoteFor(String ticker) {

		Stock stock = stocksRepository.findOne(ticker);
		BigDecimal parVal = stock.getParValue();
		BigDecimal dividRate = stock.getDividend().getDividendRate();

		StockViewDto sqv = new StockViewDto();
		sqv.setTicker(stock.getTickerSymbol());
		sqv.setType(stock.getDividend().getType());
		sqv.setParValue(parVal);

		BigDecimal currentTickerPrice = calculateTickerPriceFor(ticker,
				AppConfig.TIME_IN_MIN_INTERVAL_FOR_CACLULATING_LAST_STOCK_PRICE);

		sqv.setStockPrice(currentTickerPrice);

		if (stock.getDividend().getType().equals(SecurityType.PREFERRED)) {

			BigDecimal divid = financialMathCalculator.caclulateFixedDividendVal(parVal, dividRate);
			sqv.setFixedDividend(divid);
			sqv.setLastDividend(divid);

			BigDecimal dividYield = financialMathCalculator.calculateDividendYieldForPreferred(currentTickerPrice,
					parVal, dividRate);
			sqv.setDividendYield(dividYield);

		}

		if (stock.getDividend().getType().equals(SecurityType.COMMON)) {

			BigDecimal divid = financialMathCalculator.caclulateCommonDividendPerShareVal(currentTickerPrice,
					dividRate);
			sqv.setLastDividend(divid);
			BigDecimal dividYield = financialMathCalculator.calculateDividendYieldForCommon(currentTickerPrice, divid);
			sqv.setDividendYield(dividYield);
		}

		BigDecimal peRatio = financialMathCalculator.calculatePERatio(currentTickerPrice, sqv.getLastDividend());
		sqv.setPERatio(peRatio);

		return sqv;

	}

}
