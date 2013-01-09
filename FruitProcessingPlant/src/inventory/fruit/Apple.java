package inventory.fruit;

import factory.dimension.PointXY;
import draw.server.DrawCommandList;
import draw.Colors;


/**
 * Apple.java
 * 
 * @author:			Devin Barry
 * @date:			09.10.2012
 * @lastModified: 	09.01.2013
 * 
 * This is a concrete subclass of Fruit representing an apple
 */
public class Apple extends Fruit {
	
	private static final long serialVersionUID = -5595660658699996261L;
	
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
	
	public void draw(DrawCommandList dcl, PointXY location) {
		//Draw an apple at location
		dcl.addCommand("setPenColor", Colors.RED);
		dcl.addCommand("filledCircle", location.getX(), location.getY(), SIZE);
		if (super.getIsBad()) {
			dcl.addCommand("setPenColor", Colors.SADDLE_BROWN);
			dcl.addCommand("filledCircle", location.getX(), location.getY(), SIZE/2);
		}
		if (super.getIsCut()) {
			//fruit is cut correctly
			if (super.getIsCutCorrect()) {
				dcl.addCommand("setPenColor", Colors.WHITE);
				dcl.addCommand("text", location.getX(), location.getY(), "X");
			}
			//fruit is cut incorrectly
			else {
				dcl.addCommand("setPenColor", Colors.BLACK);
				dcl.addCommand("text", location.getX(), location.getY(), "\\");
			}
		}
	}
	
	@Override
	public String toString() {
		return new String("Apple number: " + appleID); 
	}
	
	

}
