package inventory.fruit;

import draw.StdDraw;
import factory.dimension.PointXY;
import draw.server.DrawCommandList;


/**
 * Pear.java
 * 
 * @author:			Devin Barry
 * @date:			13.10.2012
 * @lastModified: 	30.12.2012
 * 
 * This is a concrete subclass of Fruit representing a pear
 */
public class Pear extends Fruit {
	
	private static final long serialVersionUID = -3556103559750023077L;
	
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
	
	public void draw(DrawCommandList dcl, PointXY location) {
		//Draw a pear at location
		dcl.addCommand("setPenColor", StdDraw.GREEN);
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
		return new String("Pear number: " + pearID); 
	}
	
	

}
