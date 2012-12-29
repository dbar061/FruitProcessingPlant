package buffer;

import draw.Drawable;
import draw.server.DrawCommandList;
import factory.machine.InventoryConveyor;
import factory.dimension.PointXY;
import factory.dimension.ConveyorBeltDimension;


/**
 * DrawableBuffer.java
 * @author:			Devin Barry
 * @date:			13.10.2012
 * @lastModified:	30.12.2012
 * 
 * DrawableBuffer is the abstract superclass of all machines on factory floor that
 * contain a buffer that has draw methods (where some or all of the buffer spaces 
 * are drawn in the representation of the machine)
 * 
 * DrawableBuffer implements the Drawable interface and contains a copy of the
 * FactoryDimension for the machine that it is part of. It uses data from its
 * FactoryDimension to assist its draw methods.
 * 
 * Because this class needs it's buffer to be able to draw, it will usually be
 * representing a conveyor belt or similar item. ConveyorBelts and related
 * machines must be able to "advance" their belt forward. This operation is
 * represented in this class with the AdvanceBuffer method.
 * 
 * Presently, the only machine that meets these criteria is a ConveyorBelt.
 * If more machines in the future contain DrawableBuffer then we will have to
 * genericise the dimension class which we use.
 */

public abstract class DrawableBuffer extends AbstractBuffer implements Drawable, InventoryConveyor {
	
	ConveyorBeltDimension dimension;
	
	/**
	 * Constructor must be passed an initialised
	 * FactoryDimension. Currently only ConveyorBelts
	 * support drawing their buffers thus it is a 
	 * ConveyorBeltDimension.
	 * 
	 * @param d
	 */
	public DrawableBuffer(ConveyorBeltDimension d) {
		super();
		this.dimension = d;
	}
	
	/**
	 * Constructor must be passed an initialised
	 * FactoryDimension. Currently only ConveyorBelts
	 * support drawing their buffers thus it is a 
	 * ConveyorBeltDimension.
	 * 
	 * @param d
	 * @param maxSize
	 */
	public DrawableBuffer(ConveyorBeltDimension d, int maxSize) {
		super(maxSize); //set the maximum size of the AbstractBuffer
		this.dimension = d;
	}
	
	//Draws all the objects in the buffer
	//This method is called with the location argument being
	//the point where the first slot is located
	public synchronized void draw(DrawCommandList dcl, PointXY location) {
		//the location for each item in the buffer
		PointXY itemLocation = new PointXY(location);
		//an array containing all items in the buffer (for printing only)
		BufferSlot[] items = super.getPrintArray();
		
		//array must be iterated in reverse
		for (int i = (items.length-1); i >= 0; i--) {
			//dont draw empty slots
			if (items[i] != null) {
				//calls the draw method of the item actually in the buffer
				items[i].draw(dcl, itemLocation);
			}
			
			//fetches the centre point of the next slot
			itemLocation = dimension.getNextSlotDrawPoint(itemLocation);
		}
	}
	
	/**
	 * This method moves the fruit along in the buffer by
	 * a single space. This method is called instead of
	 * adding a Fruit to the buffer. When this method is
	 * called an empty space is added to the buffer instead
	 * 
	 * Advances the buffer forward by one
	 */
	public synchronized void AdvanceBuffer() {
		if (super.isFull()) {
			super.remove();
		}
		this.add(null); //add a null position to the front
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
