package com.kuzdowicz.exercises.stockmarketapp.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kuzdowicz.exercises.stockmarketapp.domain.Stock;
import com.kuzdowicz.exercises.stockmarketapp.domain.Trade;
import com.kuzdowicz.exercises.stockmarketapp.helpers.FinancialMathCalculator;
import com.kuzdowicz.exercises.stockmarketapp.repositories.StocksRepository;

@Service
@Transactional
public class StockMarketServiceImpl implements StockMarketService {

	private final StocksRepository stocksRepository;

	private final FinancialMathCalculator financialMathCalculator;

	@Autowired
	public StockMarketServiceImpl(StocksRepository stocksRepository, FinancialMathCalculator financialMathCalculator) {
		this.stocksRepository = stocksRepository;
		this.financialMathCalculator = financialMathCalculator;
	}

	@Override
	public void recordTradeForStock(String stockId, Trade trade) {
		stocksRepository.recordTradeForStock(stockId, trade);
	}

	@Override
	public List<Stock> getAllStocks() {
		return stocksRepository.findAll();
	}

	@Override
	public BigDecimal calculateLastStockPriceFor(Stock stock, int minutes) {

		List<Trade> tradesFromLastMinutes = getTradeRecordsByTimeFor(stock, minutes);

		BigDecimal currentStockPrice = financialMathCalculator.calculateStockPriceFor(tradesFromLastMinutes);
		stock.setTickerPrice(currentStockPrice);

		return currentStockPrice;
	}

	private List<Trade> getTradeRecordsByTimeFor(Stock stock, int minutes) {

		DateTime timeStampMinusMinuts = DateTime.now().minusMinutes(minutes);
		List<Trade> tradesFromLastMinutes = stocksRepository.findOne(stock.getTickerSymbol()).getTrades()//
				.parallelStream()//
				.filter(t -> t.getTimestamp().isAfter(timeStampMinusMinuts))//
				.collect(Collectors.toList());

		return tradesFromLastMinutes;
	}

	@Override
	public Stock findOneStockBy(String symbol) {
		return stocksRepository.findOne(symbol);
	}

	@Override
	public BigDecimal calculateAllShareIndex() {
		// TODO Auto-generated method stub
		return null;
	}

}
