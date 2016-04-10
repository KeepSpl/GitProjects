package demo.simplestocks;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Stock {
	
	private String symbol;
	private String type;
	private double divYield;	
	private double peRatio;
	private double price;	
	private List<Trade> trades;
	
	public Stock(){}
	
	public Stock(String symbol, String type, double dividend, double tickerPrice, double parValue, List<Trade> trades) {
		this.symbol = symbol;
		this.type = type;		
		
		if("Preferred".equals(type)) {
			divYield = dividendYieldPreferred(dividend, parValue, tickerPrice);
		} else { //If not Preferred. It is considered to be Common. 
			divYield = dividendYieldCommon(dividend, tickerPrice);
		}
		
		this.peRatio = peRatio(tickerPrice, dividend);
		this.trades = addTrades(trades); //Checks and adds the trade of the same stock symbol
		this.price = stockPrice(this.trades);		
	}
	
	public double dividendYieldCommon(double dividend, double tickerPrice) {
		double divYieldCom = dividend/tickerPrice;
		return formatValue(divYieldCom);	
	}
	
	public double dividendYieldPreferred(double fxDividend, double parValue, double tickerPrice) {		
		//Not sure about this, since it is not clear what to do with: Fixed Dividend .Par Value		
		double divParValue = (fxDividend/100) * parValue;
		double divYieldPre = divParValue/tickerPrice; 
		
		return 	formatValue(divYieldPre);
	}
	
	public double peRatio(double tickerPrice, double dividend) {
		double peRat = 0;
		
		peRat = dividend != 0 ? tickerPrice/dividend : 0;		
		
		return formatValue(peRat);
	}
	
	public double stockPrice(List<Trade> tradesList) {
		double stPrice = 0;
		double sumTradePriceQt = 0;
		double sumQt = 0;
		
		for(Trade trd : tradesList) {
			sumTradePriceQt += trd.getPrice() * trd.getQuantitytShares();
			sumQt += trd.getQuantitytShares(); 
		}
		
		stPrice = sumQt != 0 ? sumTradePriceQt/sumQt : sumTradePriceQt;		
		
		return formatValue(stPrice);
	}
	
	public static double formatValue(double val) {
		NumberFormat nf = NumberFormat.getNumberInstance(Locale.UK);
		DecimalFormat df = (DecimalFormat) nf;		
		df.setMaximumFractionDigits(3);
		
		val = Double.parseDouble(df.format(val));
		
		return val;
	}
	
	public List<Trade> getTrades() {
		return trades;
	}

	public double getPrice() {
		return price;
	}

	//Checks and adds the trade of the same stock symbol
	private List<Trade> addTrades(List<Trade> inTrades) {
		return (List<Trade>) inTrades.stream().filter(e -> e.getStockSymbol().equalsIgnoreCase(symbol)).collect(Collectors.toList());	
	}

	@Override
	public String toString() {
		return "Stock [symbol=" + symbol + ", type=" + type + ", divYield=" + divYield + ", peRatio=" + peRatio
				+ ", price=" + price + "]";
	}
}
