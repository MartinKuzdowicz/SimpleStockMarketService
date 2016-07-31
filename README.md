Simple Stock Market Service
===

### 1. Short description

Simple Java Application for Stock Market Exchange modeling
Providing functionalities like:

- Begin Session and add Stocks to Market with **StocksExchangeAdministrator** class
- Sell and buy stocks with **TradingService** interface
- Calculate dividend yield, P/E ratio and ticker prices for all traded stocks
- Stock Price is based on trades recorded in past 15 minutes
- Calculate the up to date index for all traded stocks

##### 2. How to run the application:

The runtime class with main method is **StockMarketApplication** class

To build the project and download dependencies, run in command line in the project root directory:

	mvn clean install

Then the best environment to run the application is a IDE, you can import the project in Eclipse as a maven project, then click with mouse right button and choose *Maven -> update project*, then refresh the project. Then you can right click on **StockMarketApplication** *Run as -> Java Application*, for short presentation, 

Unit tests are placed in *tests* package in *src/test/java* source folder

##### 3. Used technologies
JUNIT, Spring, log4j, maven, joda-time

##### 4. Java version used: 
Java 8





