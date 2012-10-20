package plant;

import draw.StdDraw;
import buffer.FruitBuffer;
import fruit.Fruit;
import dimension.PointXY;
import dimension.HoldingBayDimension;

/**
 * HoldingBay.java
 * @author:			Devin Barry
 * @date:			12.10.2012
 * @lastModified:	13.10.2012
 *
 * ConveyorBelt is a concrete implementation of a conveyor belt for fruit in
 * a fruit processing plant. It contains a DrawableFruitBuffer to hold the fuit and
 * process the moving of fruit along the conveyor belt.
 * 
 * It also contains a ConveyorBeltDimension, which is a special class customised
 * for dealing with the physical placement of the conveyor belt (with respect to
 * its draw methods) on the factory floor.
 * 
 * Because the DrawableFruitBuffer can draw itself, it needs access to the dimensions
 * that define this class, and thus is passed a copy of the ConveryBeltDimension
 * class when it is instantiated.
 * 
 * ConveyorBelt implements the Machine interface and as such also implements the
 * Drawable interface. Thus it contains a draw method and can draw itself.
 */
public class HoldingBay implements BufferMachine {
	
	public static final int NUM_SLOTS = 50;
	private FruitBuffer fb; //This buffer cannot draw itself
	private HoldingBayDimension hbd;
	
	public HoldingBay(PointXY start) {
		hbd = new HoldingBayDimension(start, NUM_SLOTS); // only contains startPosition
		fb = new FruitBuffer(NUM_SLOTS);
	}
	
	public void addFruit(Fruit fruit) {
		fb.add(fruit);
	}
	
	public Fruit removeFruit() {
		return fb.removeFruit();
	}
	
	/**
	 * get the length of the Machine
	 * @return machine length
	 */
	public double getLength() {
		return HoldingBayDimension.LENGTH;
	}
	
	/**
	 * get the width of the Machine
	 * @return machine width
	 */
	public double getWidth() {
		return HoldingBayDimension.WIDTH;
	}
	
	/**
	 * Gets the location at which this item is positioned
	 * @return the point where this item is located in the factory
	 */
	public PointXY getStartPoint() {
		return hbd.getStartPoint();
	}
	
	/**
	 * All classes that implement the Drawable Interface must be able to draw themselves
	 */
	public void draw(PointXY location) {
		PointXY drawPoint = hbd.getDrawPoint(location);
		Integer bufSize = new Integer(fb.getCurrentBufferSize());
		
		//Draw the HoldingBay (currently a dark gray square)
		StdDraw.setPenColor(StdDraw.DARK_GRAY);
		StdDraw.filledSquare(drawPoint, hbd.getDrawRadius());
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(drawPoint, bufSize.toString());
	}
	
	@Override
	public String toString() {
		return "HoldingBay:\n" + fb.toString(); 
	}
	

}
