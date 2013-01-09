package factory.machine;

import buffer.ProductionBuffer;

/**
 * BufferMachine.java
 * @author:			Devin Barry
 * @date:			13.10.2012
 * @lastModified:	08.01.2013
 *
 * BufferMachine interface is an extension of the Machine interface.
 * BufferMachine is used by all machinery that actually hold items
 * that get processed on the production line. In contrast the parent 
 * interface Machine can represent any machine such as a controller.
 * 
 * The other parent interface InventoryConveyor is shared not only by
 * the actual machine implementations, but also by the actual buffers
 * themselves that make up part of the machine.
 * 
 * Because all BufferMachine are Machines, they can all be drawn and
 * all have position in the factory.
 */
public interface BufferMachine extends Machine, InventoryConveyor {
	
	/**
	 * This method gets the underlying buffer from this machine
	 */
	public ProductionBuffer getProductionBuffer();
}
