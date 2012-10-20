package fruit;

import draw.StdDraw;
import dimension.PointXY;


/**
 * Apple.java
 * 
 * @author:			Devin Barry
 * @date:			09.10.2012
 * @lastModified: 	13.10.2012
 * 
 * This is a concrete subclass of Fruit representing an apple
 */
public class Apple extends Fruit {
	
	private static final int size = 10; //scaled pixels
	private static int totalApples = 0;
	private int appleID;
	 
	
	public Apple() {
		super(FruitType.APPLE);
		appleID = totalApples;
		totalApples++;
	}
	
	public void getBuffer() {
		System.out.println("No known buffer");
	}
	
	public int getTotalApples() {
		return totalApples;
	}
	
	public void draw(PointXY location) {
		//Draw an apple at location
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledCircle(location.getX(), location.getY(), size);
	}
	
	@Override
	public String toString() {
		return new String("Apple number: " + appleID + "\n"); 
	}
	
	

}
