package com.kuzdowicz.stockmarketapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuzdowicz.stockmarketapp.domain.Stock;
import com.kuzdowicz.stockmarketapp.domain.Trade;
import com.kuzdowicz.stockmarketapp.repositories.StocksRepository;

@Service
public class StockMarketTradingServiceImpl implements StockMarketTradingService {

	private final StocksRepository stocksRepository;

	@Autowired
	public StockMarketTradingServiceImpl(StocksRepository stocksRepository) {
		this.stocksRepository = stocksRepository;
	}

	@Override
	public void recordTradeForStock(Stock stock, Trade trade) {
		stock.getTrades().add(trade);
		stocksRepository.saveOrUpdate(stock);
	}

	@Override
	public List<Stock> getAllStocks() {
		return stocksRepository.findAll();
	}

}
