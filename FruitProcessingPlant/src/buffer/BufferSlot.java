package buffer;

import draw.Drawable;

/**
 * BufferSlot.java
 * @author:			Devin Barry
 * @date:			12.10.2012
 * @lastModified: 	12.10.2012
 * 
 * Abstract representation of a spacing in a buffer
 * The spacing may contain an object or be empty
 * 
 * All BufferSlots are Drawable. Even if the slot
 * is empty it will still have a draw method that
 * does nothing.
 */
public interface BufferSlot extends Drawable {
	
	public boolean isEmpty();

}
