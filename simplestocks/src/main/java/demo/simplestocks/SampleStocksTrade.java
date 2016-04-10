package demo.simplestocks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SampleStocksTrade {
	
	private static List<Trade> tradesList = new ArrayList<>();	
	
	//Trades on different stocks ongoing
	//Like a trade service.
	private static void runTrades() {		
		//Java 8 Lambda way. Cool.
		Thread tradeThread = new Thread(() -> {
			while (true) {
				//Just some random prices
				double ranPrice = Math.random()*10;
				
				//Trades
				Trade teaTrade = new Trade("TEA", LocalDateTime.now(), 5, BuySell.BOUGHT, 60 + ranPrice);
				Trade popTrade = new Trade("POP", LocalDateTime.now(), 10, BuySell.SOLD, 180 + ranPrice);
				Trade aleTrade = new Trade("ALE", LocalDateTime.now(), 15, BuySell.SOLD, 320 + ranPrice);
				Trade ginTrade = new Trade("GIN", LocalDateTime.now(), 8, BuySell.BOUGHT, 80 + ranPrice);
				Trade joeTrade = new Trade("JOE", LocalDateTime.now(), 20, BuySell.BOUGHT, 35 + ranPrice);
				
				//Add to trad list
				tradesList.add(teaTrade);
				tradesList.add(popTrade);
				tradesList.add(aleTrade);
				tradesList.add(ginTrade);
				tradesList.add(joeTrade);
				
				try {
					Thread.sleep(60*1000); //1 min
				} catch (Exception e) {
					e.printStackTrace();
				}
			}			
		});
		
		tradeThread.setDaemon(true); //Just to run as long the program runs, and stop right after no more threads
		tradeThread.start();
	}	
	
	public static void main(String[] args) {
		Stock teaStock, popStock, aleStock, ginStock, joeStock;
		
		runTrades();
		
		try {
			Thread.sleep(15*60*1000); //Trades for than 15 min.
			
			teaStock = new Stock("TEA", "Common", 0, 4, 100, tradesList);
			popStock = new Stock("POP", "Common", 8, 3, 100, tradesList);
			aleStock = new Stock("ALE", "Common", 23, 5, 60, tradesList);
			ginStock = new Stock("GIN", "Preferred", 2, 4, 100, tradesList);
			joeStock = new Stock("JOE", "Common", 13, 6, 250, tradesList);
			
			//Geometric mean. Not sure about the input, prices!!
			double prices = teaStock.getPrice()*popStock.getPrice()*aleStock.getPrice()*ginStock.getPrice()*joeStock.getPrice();
			double gm = CalGBCE.geometricMean(5, prices);
			
			System.out.println(teaStock.toString());
			System.out.println(popStock.toString());
			System.out.println(aleStock.toString());
			System.out.println(ginStock.toString());
			System.out.println(joeStock.toString()+"\n");
			
			System.out.println("Geometric mean: " + gm);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}