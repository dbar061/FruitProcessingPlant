package factory.plant;

import inventory.Inventory;
import buffer.InventoryBuffer;
import buffer.ProductionBuffer;
import factory.dimension.PointXY;
import factory.dimension.HoldingBayDimension;
import factory.machine.BufferMachine;
import draw.Colors;
import draw.server.DrawCommandList;

/**
 * HoldingBay.java
 * @author:			Devin Barry
 * @date:			12.10.2012
 * @lastModified:	09.01.2013
 *
 * TODO
 * HoldingBay needs a blurb
 */
public class HoldingBay implements BufferMachine {
	
	public static final int NUM_SLOTS = 50;
	
	private HoldingBayDimension hbd;
	private InventoryBuffer fb; //This buffer cannot draw itself
	
	public HoldingBay(PointXY start) {
		hbd = new HoldingBayDimension(start, NUM_SLOTS); // only contains startPosition
		fb = new InventoryBuffer(NUM_SLOTS);
	}
	
	/**
	 * This method is called to add Inventory to the machine
	 */
	public void addInventory(Inventory inventory) {
		fb.addInventory(inventory);
	}
	
	/**
	 * This method is called to remove Inventory from the machine
	 */
	public Inventory removeInventory() {
		return fb.removeInventory();
	}
	
	/**
	 * This method moves the Inventory along in the buffer by
	 * a single space. This method is called instead of
	 * adding Inventory to the buffer. When this method is
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
	public void draw(DrawCommandList dcl, PointXY location) {
		PointXY drawPoint = hbd.getDrawPoint(location);
		Integer bufSize = new Integer(fb.getCurrentBufferSize());
		
		//Draw the HoldingBay (currently a dark gray square)
		dcl.addCommand("setPenColor", Colors.DARK_GRAY);
		dcl.addCommand("filledSquare", drawPoint, hbd.getDrawRadius());
		dcl.addCommand("setPenColor", Colors.WHITE);
		dcl.addCommand("text", drawPoint, bufSize.toString());
	}
	
	@Override
	public String toString() {
		return "HoldingBay:\n" + fb.toString(); 
	}
	

}
