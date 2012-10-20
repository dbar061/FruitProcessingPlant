package buffer;

/**
 * ProductionBuffer.java
 * 
 * TODO consider renaming this class to MachineBuffer
 * 
 * @author:			Devin Barry
 * @date:			12.10.2012
 * @lastModified:	13.10.2012
 * 
 * An interface to be implemented by all objects that can act as "buffers"
 * in a very loose sense of the word.
 * 
 * More specifically this interface is to be implemented by all objects
 * that can hold "BufferSlot". A BufferSlot of the top level interface for
 * all objects that can be processed in our factory. This means that an
 * object that can act as a buffer is actually any machine that can process
 * fruit or other processable objects.
 * 
 * This interface will be implemented by all machinery on the factory floor
 * of our fruit processing factory. This interface is designed to unify all
 * these machines with a top level interface and a set of shared methods.
 */
public interface ProductionBuffer {
	
	public int getMaxBufferSize();
	
	public int getCurrentBufferSize();
	
	public boolean isFull();
	
	public boolean isEmpty();
	
	//Add to the back of the buffer
	public boolean add(BufferSlot bs);
	
	//Remove from the front of the buffer
	public BufferSlot remove();
	
	//move the fruit along in the buffer by one tick
	//This should possibly not be here
	public void AdvanceBuffer();

}
