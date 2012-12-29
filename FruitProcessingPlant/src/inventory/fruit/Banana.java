package inventory.fruit;

import draw.StdDraw;
import factory.dimension.PointXY;
import draw.server.DrawCommandList;

/**
 * Banana.java
 * 
 * @author:			Devin Barry
 * @date:			13.10.2012
 * @lastModified: 	30.12.2012
 * 
 * This is a concrete subclass of Fruit representing an banana
 */
public class Banana extends Fruit {
	
	private static final long serialVersionUID = 4333788593694534036L;
	
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
	
	public void draw(DrawCommandList dcl, PointXY location) {
		//Draw a banana at location
		dcl.addCommand("setPenColor", StdDraw.YELLOW);
		dcl.addCommand("filledCircle", location.getX(), location.getY(), SIZE);
		if (super.getIsBad()) {
			dcl.addCommand("setPenColor", StdDraw.SADDLE_BROWN);
			dcl.addCommand("filledCircle", location.getX(), location.getY(), SIZE/2);
		}
		if (super.getIsCut()) {
			//fruit is cut correctly
			if (super.getIsCutCorrect()) {
				dcl.addCommand("setPenColor", StdDraw.WHITE);
				dcl.addCommand("text", location.getX(), location.getY(), "X");
			}
			//fruit is cut incorrectly
			else {
				dcl.addCommand("setPenColor", StdDraw.BLACK);
				dcl.addCommand("text", location.getX(), location.getY(), "\\");
			}
		}
	}
	
	@Override
	public String toString() {
		return new String("Banana number: " + BananaID); 
	}
	
	

}
