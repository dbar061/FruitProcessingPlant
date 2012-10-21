package buffer;

import draw.Drawable;
import factory.dimension.ConveyorBeltDimension;
import factory.dimension.PointXY;


/**
 * DrawableBuffer.java
 * @author:			Devin Barry
 * @date:			13.10.2012
 * @lastModified:	22.10.2012
 * 
 * DrawableBuffer is the abstract superclass of all machines on factory floor that
 * contain a buffer that has draw methods (where some or all of the buffer spaces 
 * are drawn in the representation of the machine)
 * 
 * DrawableBuffer implements the Drawable interface and contains a copy of the
 * FactoryDimension for the machine that it is part of. It uses data from its
 * FactoryDimension to assist its draw methods.
 * 
 * Presently, the only machine that meets these criteria is a ConveyorBelt.
 * If more machines in the future contain DrawableBuffer then we will have to
 * genericise the dimension class which we use.
 */

public abstract class DrawableBuffer extends AbstractBuffer implements Drawable {
	
	ConveyorBeltDimension dimension;
	
	/**
	 * Construct must be passed an initialized FactoryDimension
	 * @param d
	 */
	public DrawableBuffer(ConveyorBeltDimension d) {
		super();
		this.dimension = d;
	}
	
	/**
	 * Construct must be passed an initialized FactoryDimension
	 * @param d
	 */
	public DrawableBuffer(ConveyorBeltDimension d, int maxSize) {
		super(maxSize); //set the maximum size of the AbstractBuffer
		this.dimension = d;
	}
	
	//Draws all the objects in the buffer
	public synchronized void draw(PointXY location) {
		//the location for each item in the buffer
		PointXY itemLocation = new PointXY(location);
		//an array containing all items in the buffer (for printing only)
		BufferSlot[] items = super.getPrintArray();
		
		for (int i = 0; i < items.length; i++) {
			//TODO This assume horizontal conveyors 
			itemLocation.x = location.x + (i * ConveyorBeltDimension.SLOT_LENGTH); //draw item in its correct slot position
			//System.out.println("Initial x location: " + location.x);
			//System.out.println("Apple x co-ord: " + itemLocation.x);
			items[i].draw(itemLocation);
		}
	}
	
	/**
	 * (non-Javadoc)
	 * @see buffer.AbstractBuffer#toString()
	 */
	@Override
	public String toString() {
		return super.toString();
	}
}
