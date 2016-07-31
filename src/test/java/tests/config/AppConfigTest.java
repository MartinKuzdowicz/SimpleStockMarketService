package tests.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kuzdowicz.exercises.stockmarketapp.database.StockMarketDB;
import com.kuzdowicz.exercises.stockmarketapp.factories.StockFactory;
import com.kuzdowicz.exercises.stockmarketapp.helpers.FinancialMathCalculator;
import com.kuzdowicz.exercises.stockmarketapp.helpers.StockDataCalculator;
import com.kuzdowicz.exercises.stockmarketapp.repositories.StocksRepository;
import com.kuzdowicz.exercises.stockmarketapp.repositories.StocksRepositoryImpl;
import com.kuzdowicz.exercises.stockmarketapp.repositories.TradesRepository;
import com.kuzdowicz.exercises.stockmarketapp.repositories.TradesRepositoryImpl;
import com.kuzdowicz.exercises.stockmarketapp.services.StocksExchangeAdministrator;
import com.kuzdowicz.exercises.stockmarketapp.services.StocksService;
import com.kuzdowicz.exercises.stockmarketapp.services.StocksServiceImpl;
import com.kuzdowicz.exercises.stockmarketapp.services.TradingService;
import com.kuzdowicz.exercises.stockmarketapp.services.TradingServiceImpl;

@Configuration
public class AppConfigTest {

	@Bean
	public StockMarketDB stockMarketDB() {
		return new StockMarketTestDB();
	}

	@Bean
	public StocksRepository stocksRepository() {
		return new StocksRepositoryImpl(stockMarketDB());
	}

	@Bean
	public TradesRepository tradesRepository() {
		return new TradesRepositoryImpl(stockMarketDB(), stocksRepository());
	}

	@Bean
	public StockDataCalculator stockDataCalculator() {
		return new StockDataCalculator(new FinancialMathCalculator());
	}

	@Bean
	public StocksService stocksService() {

		return new StocksServiceImpl(stocksRepository(), tradesRepository(), stockDataCalculator());

	}

	@Bean
	public StockFactory stockFactory() {
		return new StockFactory();
	}

	@Bean
	public StocksExchangeAdministrator admin() {
		return new StocksExchangeAdministrator(stockFactory(), stocksRepository(), stocksService());
	}

	@Bean
	public TradingService tradingService() {
		return new TradingServiceImpl(tradesRepository());
	}

}
