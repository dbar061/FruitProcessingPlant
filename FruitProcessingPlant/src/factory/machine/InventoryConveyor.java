package factory.machine;

import inventory.Inventory;

/**
 * InventoryConveyor.java
 * @author:			Devin Barry
 * @date:			23.10.2012
 * @lastModified:	24.10.2012
 * 
 * InventoryConveyor interface is an interface that describes the adding
 * removing and progressing of Inventory on a conveyor belt. This
 * interface is implemented by InventoryBuffer and all BufferMachines.
 * 
 * In some ways it is the interface that ties together the underlying
 * buffer inside the machine, and the machine itself. Both the buffer
 * inside the machine and the machine itself must implement these
 * methods. 
 */
public interface InventoryConveyor {
	
	/**
	 * This method is called to add Inventory to the machine
	 */
	public void addInventory(Inventory inventory);
	
	/**
	 * This method is called to remove Inventory from the machine
	 */
	public Inventory removeInventory();
	
	/**
	 * This method moves the Inventory along in the buffer by
	 * a single space. This method is called instead of
	 * adding Inventory to the buffer. When this method is
	 * called an empty space is added to the buffer instead
	 */
	public void AdvanceBuffer();

}
