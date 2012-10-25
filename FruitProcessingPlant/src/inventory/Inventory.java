package inventory;

import buffer.BufferSlot;
import java.io.Serializable;

/**
 * Inventory.java
 * 
 * @author:			Devin Barry
 * @date:			12.10.2012
 * @lastModified:	24.10.2012
 * 
 * Interface shared by all objects that can be processed on
 * the production line. Inventory is the top level interface
 * of all items that flow through the production line. This
 * includes the so called "empty" items, that are simply
 * space takers in the conveyor belt, used to cause the
 * conveyor belt to animate correctly.
 */
public interface Inventory extends BufferSlot, Serializable {
	
	//Which buffer type object is this Inventory object in
	//TODO future work
	public void getBuffer();

}
