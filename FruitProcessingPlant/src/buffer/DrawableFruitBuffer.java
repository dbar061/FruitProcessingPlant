package buffer;

import fruit.Fruit;
import buffer.DrawableBuffer;
import dimension.ConveyorBeltDimension;

/**
 * DrawableFruitBuffer.java
 * @author:			Devin Barry
 * @date:			12.10.2012
 * @lastModified:	13.10.2012
 *
 * DrawableFruitBuffer is a concrete subclass of DrawableBuffer.
 * A DrawableFruitBuffer is specifically designed to hold Fruit and its
 * subclasses.
 */
public class DrawableFruitBuffer extends DrawableBuffer {
	
	//Default Fruit Buffer Size is 8
	public DrawableFruitBuffer(ConveyorBeltDimension d) {
		super(d, 8);
	}
	
	//Size can be specified at creation time
	public DrawableFruitBuffer(ConveyorBeltDimension d, int size) {
		super(d, size);
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
