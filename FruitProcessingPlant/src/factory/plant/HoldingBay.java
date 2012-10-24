package factory.plant;

import inventory.Fruit;
import draw.StdDraw;
import buffer.FruitBuffer;
import buffer.ProductionBuffer;
import factory.dimension.PointXY;
import factory.dimension.HoldingBayDimension;
import factory.machine.BufferMachine;

/**
 * HoldingBay.java
 * @author:			Devin Barry
 * @date:			12.10.2012
 * @lastModified:	23.10.2012
 *
 * TODO
 * HoldingBay needs a blurb
 */
public class HoldingBay implements BufferMachine {
	
	public static final int NUM_SLOTS = 50;
	
	private HoldingBayDimension hbd;
	private FruitBuffer fb; //This buffer cannot draw itself
	
	public HoldingBay(PointXY start) {
		hbd = new HoldingBayDimension(start, NUM_SLOTS); // only contains startPosition
		fb = new FruitBuffer(NUM_SLOTS);
	}
	
	/**
	 * This method is called to add Fruit to the machine
	 */
	public void addFruit(Fruit fruit) {
		fb.addFruit(fruit);
	}
	
	/**
	 * This method is called to remove from from the machine
	 */
	public Fruit removeFruit() {
		return fb.removeFruit();
	}
	
	/**
	 * This method moves the fruit along in the buffer by
	 * a single space. This method is called instead of
	 * adding a Fruit to the buffer. When this method is
	 * called an empty space is added to the buffer instead
	 */
	public void AdvanceBuffer() {
		fb.AdvanceBuffer();
	}
	
	/**
	 * This method gets the underlying buffer from this machine
	 */
	public ProductionBuffer getProductionBuffer() {
		return fb;
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
	 * Gets the end position of this item. This end position is
	 * dependent on the size of the item. If the item is a machine
	 * on the factory floor, then this method is used for
	 * positioning other items that follow on in the production
	 * line
	 * @return the point where this item ends in the factory
	 */
	public PointXY getEndPoint() {
		return hbd.getEndPoint();
	}
	
	/**
	 * Gets the end position of this item, but factors in the
	 * machine spacing and angle of the current machine to choose
	 * an optimal starting position for the next machine on the
	 * production line.
	 * @return the point where this item ends in the factory
	 */
	public PointXY nextMachineStartPoint() {
		return hbd.nextMachineStartPoint();
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
