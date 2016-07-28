package com.kuzdowicz.exercises.stockmarketapp.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuzdowicz.exercises.stockmarketapp.domain.Stock;
import com.kuzdowicz.exercises.stockmarketapp.dto.StockViewDto;
import com.kuzdowicz.exercises.stockmarketapp.factories.StockFactory;
import com.kuzdowicz.exercises.stockmarketapp.repositories.StocksRepository;

@Service
public class ExchangeAdministrator {

	private final StockFactory stockFactory;

	private final StocksRepository stocksRepository;

	private final StockService stockService;

	@Autowired
	public ExchangeAdministrator(StockFactory stockFactory, StocksRepository stocksRepository,
			StockService stockService) {
		this.stockFactory = stockFactory;
		this.stocksRepository = stocksRepository;
		this.stockService = stockService;
	}

	public void addCommonStockToMarket(String ticker, String parVal, String lastVal, String qty, String dividendRate) {

		Stock newCommonStock = stockFactory.createCommonStock(ticker, parVal, lastVal, qty, dividendRate);
		stocksRepository.saveOrUpdate(newCommonStock);

	}

	public void addPreferredStockToMarket(String ticker, String parVal, String lastVal, String qty,
			String dividendRate) {

		Stock newCommonStock = stockFactory.createPreferredStock(ticker, parVal, lastVal, qty, dividendRate);
		stocksRepository.saveOrUpdate(newCommonStock);

	}

	public BigDecimal getAllShareIndex() {

		return stockService.calculateAllShareIndex();
	}

	public void printCurrentStockData() {

		stocksRepository.findAll().forEach(s -> {

			StockViewDto sqv = stockService.assemblyDataToStockQuote(s);

			String ticker = sqv.getTicker();
			String secType = sqv.getType();
			String lastDivid = sqv.getLastDividend();
			String fixedDivid = sqv.getFixedDividend();
			String dividendYield = sqv.getDividendYield();
			String parVal = sqv.getParValue();
			String peRatio = sqv.getPERatio();
			String price = sqv.getStockPrice();

			System.out.println(ticker + " | " + secType + " | " + lastDivid + " | " + fixedDivid + " | " + dividendYield
					+ " | " + parVal + " | " + peRatio + " | " + price);

		});

	}

}
