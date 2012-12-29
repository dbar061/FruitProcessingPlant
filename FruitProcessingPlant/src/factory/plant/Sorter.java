package factory.plant;

import inventory.Inventory;
import buffer.InventoryBuffer;
import buffer.ProductionBuffer;
import factory.dimension.PointXY;
import factory.dimension.SorterDimension;
import factory.machine.BufferMachine;
import draw.StdDraw;
import draw.server.DrawCommandList;


/**
 * Sorter.java
 * @author:			Devin Barry
 * @date:			13.10.2012
 * @lastModified:	30.12.2012
 *
 * The sorter is a form of BufferMachine
 * Unlike a conveyor belt, it only has a small number
 * of drawable slots.
 */
public class Sorter implements BufferMachine {
	
	public static final int NUM_SLOTS = 1;
	
	private SorterDimension sd;
	private InventoryBuffer fb; //This buffer cannot draw itself
	
	public Sorter(PointXY start) {
		sd = new SorterDimension(start);
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
	 * TODO this method may be implemented incorrectly
	 */
	public ProductionBuffer getProductionBuffer() {
		return fb;
	}
	
	/**
	 * get the length of the Machine
	 * @return machine length
	 */
	public double getLength() {
		return SorterDimension.RADIUS;
	}
	
	/**
	 * get the width of the Machine
	 * @return machine width
	 */
	public double getWidth() {
		return SorterDimension.RADIUS;
	}
	
	/**
	 * Gets the location at which this item is positioned
	 * @return the point where this item is located in the factory
	 */
	public PointXY getStartPoint() {
		return sd.getStartPoint();
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
		return sd.getEndPoint();
	}
	
	/**
	 * Gets the end position of this item, but factors in the
	 * machine spacing and angle of the current machine to choose
	 * an optimal starting position for the next machine on the
	 * production line.
	 * @return the point where this item ends in the factory
	 */
	public PointXY nextMachineStartPoint() {
		return sd.nextMachineStartPoint();
	}
	
	/**
	 * This method causes the current object to draw itself
	 * at the location specified by <location>
	 * @param location
	 */
	public void draw(DrawCommandList dcl, PointXY location) {
		PointXY drawPoint = sd.getDrawPoint(location);
		
		//Draw the Sorter
		dcl.addCommand("setPenColor", StdDraw.CYAN);
		dcl.addCommand("filledCircle", drawPoint, sd.getDrawRadius());
		dcl.addCommand("setPenColor", StdDraw.BLUE);
		dcl.addCommand("circle", drawPoint, sd.getDrawRadius());
	}

}
