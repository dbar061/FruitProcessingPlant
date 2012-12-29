package buffer;

import inventory.Inventory;
import factory.machine.InventoryConveyor;

/**
 * InventoryBuffer.java
 * @author:			Devin Barry
 * @date:			13.10.2012
 * @lastModified:	30.12.2012
 *
 * InventoryBuffer is a concrete subclass of AbstractBuffer and the close cousin
 * of DrawableInventoryBuffer.
 * 
 * Unlike DrawableInventoryBuffer, InventoryBuffer cannot draw itself and it is used
 * in machines where the InventoryBuffer does not draw.
 * 
 * A InventoryBuffer is specifically designed to hold Inventory and its subclasses.
 * 
 * Unlike DrawableInventoryBuffer, InventoryBuffer never stores null elements. This
 * buffer actually works as a buffer (to buffer real items) and is not part
 * of a animated conveyor or similar part. Thus it only contains real Inventory
 * never null.
 * TODO this class now extends InventoryConveyor. This may be incorrect!
 */
public class InventoryBuffer extends AbstractBuffer implements InventoryConveyor {
	
	//Default Fruit Buffer Size is 8
	public InventoryBuffer() {
		super(8);
	}
	
	//Size can be specified at creation time
	public InventoryBuffer(int size) {
		super(size);
	}
	
	//Convenience method for adding Fruit to the end of the buffer
	public void addInventory(Inventory inventory) {
		if (inventory == null) return; //do not allow null items to be stored in a InventoryBuffer
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
	
	/**
	 * This method moves the fruit along in the buffer by
	 * a single space. This method is called instead of
	 * adding a Fruit to the buffer. When this method is
	 * called an empty space is added to the buffer instead
	 * 
	 * Advances the buffer forward by one.
	 */
	public synchronized void AdvanceBuffer() {
		//no action occurs for AdvanceBuffer when called on InventoryBuffer.
		//We don't store null objects in the Buffer when it cannot draw
		//30.12.2012 - Not sure what is going on here
		//TODO
	}
}
