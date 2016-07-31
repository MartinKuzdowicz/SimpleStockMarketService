package com.kuzdowicz.exercises.stockmarketapp.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kuzdowicz.exercises.stockmarketapp.config.AppConfig;
import com.kuzdowicz.exercises.stockmarketapp.constants.SecurityType;
import com.kuzdowicz.exercises.stockmarketapp.domain.Stock;
import com.kuzdowicz.exercises.stockmarketapp.domain.Trade;
import com.kuzdowicz.exercises.stockmarketapp.dto.StockViewDto;
import com.kuzdowicz.exercises.stockmarketapp.helpers.StockDataCalculator;
import com.kuzdowicz.exercises.stockmarketapp.repositories.StocksRepository;
import com.kuzdowicz.exercises.stockmarketapp.repositories.TradesRepository;

@Service
@Transactional
public class StocksServiceImpl implements StocksService {

	private final static int TICKER_PRICE_EXPIRE_TIME_IN_MINUTES = AppConfig.TIME_IN_MIN_INTERVAL_FOR_CACLULATING_LAST_STOCK_PRICE;

	private final StocksRepository stocksRepository;

	private final TradesRepository tradesRepository;

	private final StockDataCalculator stockDataCalculator;

	@Autowired
	public StocksServiceImpl(StocksRepository stocksRepository, TradesRepository tradesRepository,
			StockDataCalculator stockDataCalculator) {
		this.stocksRepository = stocksRepository;
		this.tradesRepository = tradesRepository;
		this.stockDataCalculator = stockDataCalculator;
	}

	@Override
	public BigDecimal calculateTickerPriceFor(String ticker) {

		List<Trade> tradesFromLastMinutes = tradesRepository.getTradesByTickerRecordedBefore(ticker,
				TICKER_PRICE_EXPIRE_TIME_IN_MINUTES);

		if (tradesFromLastMinutes.isEmpty()) {

			if (stocksRepository.findOne(ticker).getLastPrice() != null) {

				return stocksRepository.findOne(ticker).getLastPrice();

			}

			return stocksRepository.findOne(ticker).getOpeningPrice();

		} else {

			BigDecimal currentStockPrice = stockDataCalculator.calculateStockPriceFromTrades(tradesFromLastMinutes);
			stocksRepository.updateTickerPrice(ticker, currentStockPrice);
			return currentStockPrice;
		}

	}

	@Override
	public BigDecimal calculateAllShareIndex() {

		List<BigDecimal> stocksMarketCaps = stocksRepository.findAll().stream()//
				.map(s -> calculateTickerPriceFor(s.getTickerSymbol())//
						.multiply(new BigDecimal(s.getNrOfOutstandingShares())))//
				.collect(Collectors.toList());

		return stockDataCalculator.calculateAllShareIndexFrom(stocksMarketCaps);
	}

	@Override
	public BigDecimal calculateLastDividendFor(String ticker, BigDecimal tickerPrice) {

		Stock stock = stocksRepository.findOne(ticker);

		return stockDataCalculator.calculateLastDividendFor(stock, tickerPrice);
	}

	@Override
	public BigDecimal caclulateDividendYieldFor(String ticker, BigDecimal tickerPrice, BigDecimal lastDividenVal) {

		Stock stock = stocksRepository.findOne(ticker);
		return stockDataCalculator.calculateDividendYieldFor(stock, tickerPrice, lastDividenVal);
	}

	public StockViewDto assemblyAllCalculationsToStockQuoteFor(String ticker) {

		Stock stock = stocksRepository.findOne(ticker);

		BigDecimal parVal = stock.getParValue();

		StockViewDto sqv = new StockViewDto();
		sqv.setTicker(stock.getTickerSymbol());
		sqv.setType(stock.getDividend().getType());
		sqv.setParValue(parVal);

		BigDecimal currentTickerPrice = calculateTickerPriceFor(ticker);
		sqv.setStockPrice(currentTickerPrice);

		BigDecimal lastDividendVal = stockDataCalculator.calculateLastDividendFor(stock, currentTickerPrice);
		sqv.setLastDividend(lastDividendVal);

		BigDecimal dividYield = stockDataCalculator.calculateDividendYieldFor(stock, currentTickerPrice,
				lastDividendVal);
		sqv.setDividendYield(dividYield);

		BigDecimal peRatio = stockDataCalculator.calculatePERatio(currentTickerPrice, lastDividendVal);
		sqv.setPERatio(peRatio);

		if (stock.getDividend().getType().equals(SecurityType.PREFERRED)) {
			sqv.setFixedDividend(lastDividendVal);
		}
		
		sqv.setScaleInBigDecimalFieldsForPresentation();

		return sqv;

	}

	@Override
	public Set<String> getTickerSymbols() {
		return stocksRepository.findAllTickerSymbols();
	}

}
