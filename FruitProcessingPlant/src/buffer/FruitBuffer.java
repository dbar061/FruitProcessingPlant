package buffer;

import fruit.Fruit;

/**
 * FruitBuffer.java
 * @author:			Devin Barry
 * @date:			13.10.2012
 * @lastModified:	13.10.2012
 *
 * FruitBuffer is a concrete subclass of AbstractBuffer and the close cousin
 * of DrawableFruitBuffer.
 * 
 * Unlike DrawableFruitBuffer, FruitBuffer cannot draw itself and it is used
 * in machines where the FruitBuffer does not draw.
 * 
 * A FruitBuffer is specifically designed to hold Fruit and its subclasses.
 */
public class FruitBuffer extends AbstractBuffer {
	
	//Default Fruit Buffer Size is 8
	public FruitBuffer() {
		super(8);
	}
	
	//Size can be specified at creation time
	public FruitBuffer(int size) {
		super(size);
	}
	
	//Convenience method for adding Fruit to the end of the buffer
	public void addFruit(Fruit fruit) {
		super.add(fruit);
	}
	
	//Convenience method for removing Fruit from the front of the buffer
	public Fruit removeFruit() {
		BufferSlot bs = super.remove();
		if (bs instanceof Fruit) {
			return (Fruit)bs;
		}
		throw new RuntimeException("Error, Buffer does not contain Fruit");
	}
}
