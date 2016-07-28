package com.kuzdowicz.exercises.stockmarketapp.repositories;

import java.util.List;

import com.kuzdowicz.exercises.stockmarketapp.domain.Trade;

public interface TradesRepository {

	void recordTrade(Trade trade);

	List<Trade> findTradesFor(String ticker);
	
	List<Trade> getTradesByTickerRecordedBefore(String ticker, int minutes);

}
