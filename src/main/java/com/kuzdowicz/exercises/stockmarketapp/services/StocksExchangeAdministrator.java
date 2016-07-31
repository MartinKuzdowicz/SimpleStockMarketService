package com.kuzdowicz.exercises.stockmarketapp.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuzdowicz.exercises.stockmarketapp.domain.Stock;
import com.kuzdowicz.exercises.stockmarketapp.dto.StockViewDto;
import com.kuzdowicz.exercises.stockmarketapp.factories.StockFactory;
import com.kuzdowicz.exercises.stockmarketapp.repositories.StocksRepository;

@Service
public class StocksExchangeAdministrator {

	private final StockFactory stockFactory;

	private final StocksRepository stocksRepository;

	private final StocksService stocksService;

	@Autowired
	public StocksExchangeAdministrator(StockFactory stockFactory, StocksRepository stocksRepository,
			StocksService stocksService) {
		this.stockFactory = stockFactory;
		this.stocksRepository = stocksRepository;
		this.stocksService = stocksService;
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

		return stocksService.calculateAllShareIndex().setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public void printCurrentStockData() {

		stocksRepository.findAllTickerSymbols().forEach(tickerSym -> {

			StockViewDto sqv = stocksService.assemblyAllCalculationsToStockQuoteFor(tickerSym);

			String ticker = sqv.getTicker();
			String secType = sqv.getType().name();
			String lastDivid = sqv.getLastDividend().toString();
			String fixedDivid = sqv.getFixedDividend() != null ? sqv.getFixedDividend().toString() : " ";
			String dividendYield = sqv.getDividendYield().toString();
			String parVal = sqv.getParValue().toString();
			String peRatio = sqv.getPERatio().toString();
			String price = sqv.getStockPrice().toString();

			System.out.println(ticker + " | " + secType + " | " + lastDivid + " | " + fixedDivid + " | " + dividendYield
					+ " | " + parVal + " | " + peRatio + " | " + price);

		});

	}

}
