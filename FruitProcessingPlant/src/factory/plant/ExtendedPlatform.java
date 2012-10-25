package factory.plant;

import inventory.Inventory;
import buffer.InventoryBuffer;
import buffer.ProductionBuffer;
import factory.dimension.PointXY;
import factory.dimension.ExtendedPlatformDimension;
import factory.machine.Machine;
import draw.StdDraw;


/**
 * ExtendedPlatform.java
 * @author:			Devin Barry
 * @date:			13.10.2012
 * @lastModified:	25.10.2012
 *
 * TODO
 * ExtendedPlatform has two inputs and one output
 * Each input is buffered and so is the output.
 * 
 * Inventory arriving at each input buffer is placed
 * into the output buffer
 */
public class ExtendedPlatform implements Machine {
	
	private ExtendedPlatformDimension epd;
	private InventoryBuffer ibInput1; //This buffer cannot draw itself
	private InventoryBuffer ibInput2; //This buffer cannot draw itself
	private InventoryBuffer ibOutput; //This buffer cannot draw itself
	
	public ExtendedPlatform(PointXY start) {
		epd = new ExtendedPlatformDimension(start);
		ibInput1 = new InventoryBuffer(10);
		ibInput2 = new InventoryBuffer(10);
		ibOutput = new InventoryBuffer(2);
	}
	
	/**
	 * This method is called to add Inventory to the 
	 * first input buffer
	 */
	public void addInput1(Inventory inventory) {
		ibInput1.addInventory(inventory);
	}
	
	/**
	 * This method is called to add Inventory to the 
	 * first input buffer
	 */
	public void addInput2(Inventory inventory) {
		ibInput2.addInventory(inventory);
	}
	
	/**
	 * This method is called to add Inventory to the 
	 * first input buffer
	 */
	public void addOutput(Inventory inventory) {
		ibOutput.addInventory(inventory);
	}
	
	/**
	 * This method is called to remove Inventory from the
	 * first input buffer
	 */
	public Inventory removeInput1() {
		return ibInput1.removeInventory();
	}
	
	/**
	 * This method is called to remove Inventory from the
	 * first input buffer
	 */
	public Inventory removeInput2() {
		return ibInput2.removeInventory();
	}
	
	/**
	 * This method is called to remove Inventory from the
	 * first input buffer
	 */
	public Inventory removeOutput() {
		return ibOutput.removeInventory();
	}
	
	/**
	 * This method gets the underlying buffer from this machine
	 */
	public ProductionBuffer getPBInput1() {
		return ibInput1;
	}
	
	/**
	 * This method gets the underlying buffer from this machine
	 */
	public ProductionBuffer getPBInput2() {
		return ibInput2;
	}
	
	/**
	 * This method gets the underlying buffer from this machine
	 */
	public ProductionBuffer getPBOutput() {
		return ibOutput;
	}
	
	/**
	 * get the length of the Machine
	 * @return machine length
	 */
	public double getLength() {
		return ExtendedPlatformDimension.RADIUS;
	}
	
	/**
	 * get the width of the Machine
	 * @return machine width
	 */
	public double getWidth() {
		return ExtendedPlatformDimension.RADIUS;
	}
	
	/**
	 * Gets the location at which this item is positioned
	 * @return the point where this item is located in the factory
	 */
	public PointXY getStartPoint() {
		return epd.getStartPoint();
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
		return epd.getEndPoint();
	}
	
	/**
	 * Gets the end position of this item, but factors in the
	 * machine spacing and angle of the current machine to choose
	 * an optimal starting position for the next machine on the
	 * production line.
	 * @return the point where this item ends in the factory
	 */
	public PointXY nextMachineStartPoint() {
		return epd.nextMachineStartPoint();
	}
	
	/**
	 * This method causes the current object to draw itself
	 * at the location specified by <location>
	 * @param location
	 */
	public void draw(PointXY location) {
		PointXY drawPoint = epd.getDrawPoint(location);
		
		//Draw the ExtendedPlatform
		StdDraw.setPenColor(StdDraw.BOOK_BLUE);
		StdDraw.filledDiamond(drawPoint, epd.getDrawRadius());
	}

}
