package demo.simplestocks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.Assert;

public class StockTest {
	
	@Test
	public void testDividendYieldCommon() {
		Stock st = new Stock();
		double divYieldCom1 = st.dividendYieldCommon(8, 4);
		double divYieldCom2 = st.dividendYieldCommon(9, 2);
		
		Assert.assertEquals(2.0, divYieldCom1);
		Assert.assertEquals(4.5, divYieldCom2);
	}
	  
	@Test
	public void testDividendYieldPreferred() {
		Stock st = new Stock();
		double divYieldPre1 = st.dividendYieldPreferred(2, 100, 4);
		double divYieldPre2 = st.dividendYieldPreferred(4, 100, 4);
		
		Assert.assertEquals(0.5, divYieldPre1);
		Assert.assertEquals(1.0, divYieldPre2);
	}
	
	@Test
	public void testPeRatio() {
		Stock st = new Stock();
		double peRat1 = st.peRatio(4, 8);
		double peRat2 = st.peRatio(10, 25);
		
		Assert.assertEquals(0.5, peRat1);
		Assert.assertEquals(0.4, peRat2);
	}
	
	@Test	
	public void testStockPrice() {
		Stock st = new Stock();
		Trade trade1 = new Trade("POP", LocalDateTime.now(), 3, BuySell.BOUGHT, 80);		
		Trade trade2 = new Trade("POP", LocalDateTime.now(), 5, BuySell.SOLD, 50);	
		List<Trade> SampleTrades = new ArrayList<Trade>();	
		
		SampleTrades.add(trade1);
		SampleTrades.add(trade2);	
		
		double stPrice = st.stockPrice(SampleTrades);
		
		Assert.assertEquals(61.25, stPrice);
	}
	
	@Test
	public void testStockConstructor() {
		Trade trade1 = new Trade("POP", LocalDateTime.now(), 3, BuySell.BOUGHT, 80);		
		Trade trade2 = new Trade("POP", LocalDateTime.now(), 5, BuySell.SOLD, 50);
		Trade trade3 = new Trade("TEA", LocalDateTime.now(), 4, BuySell.SOLD, 40);
		Trade trade4 = new Trade("TEA", LocalDateTime.now(), 2, BuySell.SOLD, 70);
		Trade trade5 = new Trade("GIN", LocalDateTime.now(), 2, BuySell.SOLD, 70);		
		List<Trade> SampleTrades = new ArrayList<>();	
		
		SampleTrades.add(trade1);
		SampleTrades.add(trade2);
		SampleTrades.add(trade3);
		SampleTrades.add(trade4);
		SampleTrades.add(trade5);		
		
		Stock st1 = new Stock("POP", "Common", 8, 4, 100, SampleTrades);
		Stock st2 = new Stock("TEA", "Common", 0, 6, 100, SampleTrades);
		Stock st3 = new Stock("ALE", "Common", 23, 5, 60, SampleTrades); //No trades yet
		Stock st4 = new Stock("GIN", "Preferred", 8, 6, 100, SampleTrades); 		
		
		System.out.println(st1.toString());
		System.out.println(st2.toString());
		System.out.println(st3.toString());
		System.out.println(st4.toString());
	}
	
	@Test
	public void testStockTrades() {
		Trade trade1 = new Trade("POP", LocalDateTime.now(), 3, BuySell.BOUGHT, 80);		
		Trade trade2 = new Trade("POP", LocalDateTime.now(), 5, BuySell.SOLD, 50);
		Trade trade3 = new Trade("ALE", LocalDateTime.now(), 3, BuySell.BOUGHT, 80);		
		Trade trade4 = new Trade("JOE", LocalDateTime.now(), 5, BuySell.SOLD, 50);
		Trade trade5 = new Trade("GIN", LocalDateTime.now(), 3, BuySell.BOUGHT, 80);		
		Trade trade6 = new Trade("TEA", LocalDateTime.now(), 5, BuySell.SOLD, 50);
		List<Trade> SampleTrades = new ArrayList<>();	
		
		SampleTrades.add(trade1);
		SampleTrades.add(trade2);
		SampleTrades.add(trade3);
		SampleTrades.add(trade4);
		SampleTrades.add(trade5);
		SampleTrades.add(trade6);
		
		Stock st = new Stock("POP", "Common", 8, 4, 100, SampleTrades);
		
		for(Trade trd : st.getTrades()) {
			Assert.assertEquals("POP", trd.getStockSymbol());
		}		
	}
	
	//This should have been in another test class. But this is just for test :) 
	@Test
	public void testGeometricMean() {		
		double val1 = CalGBCE.geometricMean(2, 4);
		double val2 = CalGBCE.geometricMean(3, 27);
		double val3 = CalGBCE.geometricMean(5, 7776);
		double val4 = CalGBCE.geometricMean(3, 10);
		
		Assert.assertEquals(2.0, val1, 0);
		Assert.assertEquals(3.0, val2, 0);
		Assert.assertEquals(6.0, val3, 0);
		Assert.assertEquals(2.154, val4, 0);
	}

}
