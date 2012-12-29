package buffer;

import inventory.Inventory;
import buffer.DrawableBuffer;
import factory.machine.InventoryConveyor;
import factory.dimension.ConveyorBeltDimension;

/**
 * DrawableInventoryBuffer.java
 * @author:			Devin Barry
 * @date:			12.10.2012
 * @lastModified:	30.12.2012
 *
 * DrawableInventoryBuffer is a concrete subclass of DrawableBuffer.
 * A DrawableInventoryBuffer is specifically designed to hold Inventory and its
 * subclasses. Fruit is subclass of Inventory.
 * 
 * DrawableInventoryBuffer also implements the InventoryConveyor interface,
 * requiring it to be able to add Inventory, remove Inventory and advance items
 * along its buffer.
 */
public class DrawableInventoryBuffer extends DrawableBuffer implements InventoryConveyor {
	
	//Default Fruit Buffer Size is 8
	public DrawableInventoryBuffer(ConveyorBeltDimension d) {
		super(d, 8);
	}
	
	//Size can be specified at creation time
	public DrawableInventoryBuffer(ConveyorBeltDimension d, int size) {
		super(d, size);
	}
	
	//Convenience method for adding Inventory to the beginning of the buffer
	public void addInventory(Inventory inventory) {
		super.add(inventory);
	}
	
	//Convenience method for removing Inventory from the front of the buffer
	public Inventory removeInventory() {
		BufferSlot bs = super.remove();
		if (bs instanceof Inventory) {
			return (Inventory)bs;
		}
		throw new RuntimeException("Error, Buffer does not contain Inventory");
	}
}
