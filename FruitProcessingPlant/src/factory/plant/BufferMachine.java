package factory.plant;

import buffer.ProductionBuffer;
import fruit.Fruit;

/**
 * BufferMachine.java
 * @author:			Devin Barry
 * @date:			13.10.2012
 * @lastModified:	13.10.2012
 *
 * BufferMachine interface is an extension of the Machine interface.
 * BufferMachine is used by all machinery that actually hold items
 * that get processed on the production line. In contrast the parent 
 * interface Machine can represent any machine such as a controller.
 * 
 * Because all BufferMachine are Machines, they can all be drawn.
 */
public interface BufferMachine extends Machine {
	
	/**
	 * This method is called to add Fruit to the machine
	 */
	public void addFruit(Fruit fruit);
	
	/**
	 * This method is called to remove from from the machine
	 */
	public Fruit removeFruit();
	
	/**
	 * This method gets the underlying buffer from this machine
	 */
	public ProductionBuffer getProductionBuffer();
}
