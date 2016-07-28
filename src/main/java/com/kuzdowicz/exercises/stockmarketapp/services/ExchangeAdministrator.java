package com.kuzdowicz.exercises.stockmarketapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuzdowicz.exercises.stockmarketapp.domain.Stock;
import com.kuzdowicz.exercises.stockmarketapp.factories.StockFactory;
import com.kuzdowicz.exercises.stockmarketapp.repositories.StocksRepository;

@Service
public class ExchangeAdministrator {

	private final StockFactory stockFactory;

	private final StocksRepository stocksRepository;

	@Autowired
	public ExchangeAdministrator(StockFactory stockFactory, StocksRepository stocksRepository) {
		this.stockFactory = stockFactory;
		this.stocksRepository = stocksRepository;
	}

	public void addCommonStockToMarket(String ticker, String parVal, String qty, String dividendRate) {

		Stock newCommonStock = stockFactory.createCommonStock(ticker, parVal, qty, dividendRate);
		stocksRepository.saveOrUpdate(newCommonStock);

	}

	public void addPreferredStockToMarket(String ticker, String parVal, String qty, String dividendRate) {

		Stock newCommonStock = stockFactory.createPreferredStock(ticker, parVal, qty, dividendRate);
		stocksRepository.saveOrUpdate(newCommonStock);

	}

}
