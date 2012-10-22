package factory.machine;

import fruit.Fruit;

/**
 * FruitConveyor.java
 * @author:			Devin Barry
 * @date:			23.10.2012
 * @lastModified:	23.10.2012
 * 
 * FruitConveyor interface is an interface that describes the adding
 * removing and progressing of Fruits on a conveyor belt. This
 * interface is implemented by FruitBuffer and all BufferMachines.
 * 
 * In some ways it is the interface that ties together the underlying
 * buffer inside the machine, and the machine itself. Both the buffer
 * inside the machine and the machine itself must implement these
 * methods. 
 */
public interface FruitConveyor {
	
	/**
	 * This method is called to add Fruit to the machine
	 */
	public void addFruit(Fruit fruit);
	
	/**
	 * This method is called to remove from from the machine
	 */
	public Fruit removeFruit();
	
	/**
	 * This method moves the fruit along in the buffer by
	 * a single space. This method is called instead of
	 * adding a Fruit to the buffer. When this method is
	 * called an empty space is added to the buffer instead
	 */
	public void AdvanceBuffer();

}
