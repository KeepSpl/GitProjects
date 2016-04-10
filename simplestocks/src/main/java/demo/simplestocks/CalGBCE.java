package demo.simplestocks;

public class CalGBCE {
	
	public static double geometricMean(double n, double price) {		
		//n'th square root  
		double upVal = 1/n;		
		double gm = Math.pow(price, upVal);
		
		return Stock.formatValue(gm);	
	}

}
