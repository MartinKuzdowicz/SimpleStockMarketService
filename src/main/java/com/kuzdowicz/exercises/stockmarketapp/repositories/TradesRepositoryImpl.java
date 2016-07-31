package com.kuzdowicz.exercises.stockmarketapp.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kuzdowicz.exercises.stockmarketapp.database.StockMarketDB;
import com.kuzdowicz.exercises.stockmarketapp.domain.Trade;

@Repository
@Transactional
public class TradesRepositoryImpl implements TradesRepository {

	private final StockMarketDB stockMarketAppDB;

	private final Map<String, List<Trade>> trades;

	private final StocksRepository stocksRepository;

	@Autowired
	public TradesRepositoryImpl(StockMarketDB stockMarketAppDB, StocksRepository stocksRepository) {
		this.stockMarketAppDB = stockMarketAppDB;
		this.stocksRepository = stocksRepository;
		trades = this.stockMarketAppDB.getTrades();
	}

	@Override
	public void recordTrade(Trade trade) {

		String ticker = trade.getTicker();
		if (trades.containsKey(ticker)) {

			trades.get(ticker).add(trade);
			stocksRepository.updateLastPrice(ticker, trade.getPrice());

		} else {

			List<Trade> tradesForGivenTicker = new ArrayList<>();
			tradesForGivenTicker.add(trade);
			trades.put(ticker, tradesForGivenTicker);

		}

	}

	@Override
	public List<Trade> findTradesFor(String ticker) {
		return trades.get(ticker);
	}

	@Override
	public List<Trade> getTradesByTickerRecordedBefore(String ticker, int minutes) {

		if (findTradesFor(ticker) == null || findTradesFor(ticker).isEmpty()) {
			return new ArrayList<>();
		}

		DateTime timeStampMinusMinuts = DateTime.now().minusMinutes(minutes);
		List<Trade> tradesFromLastMinutes = findTradesFor(ticker)//
				.parallelStream()//
				.filter(t -> t.getTimestamp().isAfter(timeStampMinusMinuts))//
				.collect(Collectors.toList());

		return tradesFromLastMinutes;
	}

}
