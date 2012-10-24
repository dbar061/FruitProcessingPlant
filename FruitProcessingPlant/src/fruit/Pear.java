package fruit;

import draw.StdDraw;
import factory.dimension.PointXY;


/**
 * Pear.java
 * 
 * @author:			Devin Barry
 * @date:			13.10.2012
 * @lastModified: 	24.10.2012
 * 
 * This is a concrete subclass of Fruit representing a pear
 */
public class Pear extends Fruit {
	
	private static final int SIZE = 10; //scaled pixels
	private static int totalPears = 0;
	private int pearID;
	 
	
	public Pear() {
		super(FruitType.PEAR);
		pearID = totalPears;
		totalPears++;
	}
	
	public void getBuffer() {
		System.out.println("No known buffer");
	}
	
	public int getTotalPears() {
		return totalPears;
	}
	
	public void draw(PointXY location) {
		//Draw a pear at location
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.filledCircle(location.getX(), location.getY(), SIZE);
	}
	
	@Override
	public String toString() {
		return new String("Pear number: " + pearID); 
	}
	
	

}
