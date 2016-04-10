package demo.simplestocks;

import java.time.LocalDateTime;

public class Trade {
	private String stockSymbol;
	private LocalDateTime timestamp;
	private int quantitytShares;
	private BuySell indicator;
	private double price;
	
	public Trade(String stockSymbol, LocalDateTime timestamp, int quantitytShares, BuySell indicator, double price) {
		this.stockSymbol = stockSymbol;
		this.timestamp = timestamp;
		this.quantitytShares = quantitytShares; 
		this.indicator = indicator;
		this.price = price;
	}	
	
	public String getStockSymbol() {
		return stockSymbol;
	}
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}		
	
	public int getQuantitytShares() {
		return quantitytShares;
	}	

	public BuySell getIndicator() {
		return indicator;
	}

	public double getPrice() {
		return price;
	}		
}
