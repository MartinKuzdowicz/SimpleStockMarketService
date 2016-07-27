package com.kuzdowicz.exercises.stockmarketapp.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

public class TradingServiceImpl implements TradingService {

	private final StockService stockMarketService;

	@Autowired
	public TradingServiceImpl(StockService stockMarketService) {
		this.stockMarketService = stockMarketService;
	}

	@Override
	public BigDecimal getAllShareIndex() {

		return stockMarketService.calculateAllShareIndex();
	}

	@Override
	public void byStock(String ticker) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sellStock(String ticker) {
		// TODO Auto-generated method stub
		
	}

}
