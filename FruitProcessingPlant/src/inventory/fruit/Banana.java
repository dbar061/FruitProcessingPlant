package inventory.fruit;

import draw.StdDraw;
import factory.dimension.PointXY;


/**
 * Banana.java
 * 
 * @author:			Devin Barry
 * @date:			13.10.2012
 * @lastModified: 	24.10.2012
 * 
 * This is a concrete subclass of Fruit representing an banana
 */
public class Banana extends Fruit {
	
	private static final int SIZE = 10; //scaled pixels
	private static int totalBananas = 0;
	private int BananaID;
	 
	
	public Banana() {
		super(FruitType.BANANA);
		BananaID = totalBananas;
		totalBananas++;
	}
	
	public void getBuffer() {
		System.out.println("No known buffer");
	}
	
	public int getTotaltotalBananas() {
		return totalBananas;
	}
	
	public void draw(PointXY location) {
		//Draw a banana at location
		StdDraw.setPenColor(StdDraw.YELLOW);
		StdDraw.filledCircle(location.getX(), location.getY(), SIZE);
		if (super.getIsBad()) {
			StdDraw.setPenColor(StdDraw.SADDLE_BROWN);
			StdDraw.filledCircle(location.getX(), location.getY(), SIZE/2);
		}
		if (super.getIsCut()) {
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(location.getX(), location.getY(), "X");
		}
	}
	
	@Override
	public String toString() {
		return new String("Banana number: " + BananaID); 
	}
	
	

}
