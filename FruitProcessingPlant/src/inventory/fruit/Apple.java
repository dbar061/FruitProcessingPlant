package inventory.fruit;

import draw.StdDraw;
import factory.dimension.PointXY;


/**
 * Apple.java
 * 
 * @author:			Devin Barry
 * @date:			09.10.2012
 * @lastModified: 	24.10.2012
 * 
 * This is a concrete subclass of Fruit representing an apple
 */
public class Apple extends Fruit {
	
	private static final int SIZE = 10; //scaled pixels
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
		StdDraw.filledCircle(location.getX(), location.getY(), SIZE);
		if (super.getIsBad()) {
			StdDraw.setPenColor(StdDraw.SADDLE_BROWN);
			StdDraw.filledCircle(location.getX(), location.getY(), SIZE/2);
		}
		if (super.getIsCut()) {
			//StdDraw.setPenRadius();
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(location.getX(), location.getY(), "X");
			//StdDraw.setPenColor(StdDraw.WHITE);
			//StdDraw.text(0.8, 0.8, "white text");
		}
	}
	
	@Override
	public String toString() {
		return new String("Apple number: " + appleID); 
	}
	
	

}
