package com.kuzdowicz.exercises.stockmarketapp.services;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuzdowicz.exercises.stockmarketapp.constants.Indicator;
import com.kuzdowicz.exercises.stockmarketapp.domain.Trade;
import com.kuzdowicz.exercises.stockmarketapp.repositories.TradesRepository;

@Service
public class TradingServiceImpl implements TradingService {

	private final TradesRepository tradesRepository;

	@Autowired
	public TradingServiceImpl(TradesRepository tradesRepository) {
		this.tradesRepository = tradesRepository;
	}

	@Override
	public void byStock(String ticker, BigDecimal price, BigInteger qty) {

		Trade newBuyTradeAction = new Trade(ticker, price, qty, Indicator.BUY, DateTime.now());

		tradesRepository.recordTrade(newBuyTradeAction);

	}

	@Override
	public void sellStock(String ticker, BigDecimal price, BigInteger qty) {

		Trade newSellTradeAction = new Trade(ticker, price, qty, Indicator.SELL, DateTime.now());

		tradesRepository.recordTrade(newSellTradeAction);

	}

}
