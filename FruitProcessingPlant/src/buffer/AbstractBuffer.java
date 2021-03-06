package buffer;

import java.util.*;


/**
 * AbstractBuffer.java
 * @author:			Devin Barry
 * @date:			12.10.2012
 * @lastModified:	28.10.2012
 *
 * AbstractBuffer implements the ProductionBuffer interface and is the parent class for
 * any piece of machinery that can hold "fruit" (actually BufferSlot).
 * 
 * AbstractBuffer is abstract and is capable of representing any machine on the production
 * floor that can buffer items.
 * 
 * This class deals only with the mechanics of buffering and moving data through buffer
 * slots. The physical constaints of the machine which hold this buffer and how to draw
 * the buffer are not dealt with in this class.
 * 
 * There is however an access method that is required for drawing that is exposed in this
 * class, which is the getPrintArray() method.
 * 
 * This class uses a PrintableQueue class as its underlying buffer. It makes calls to
 * this PrintableQueue to implement its functionality.
 */

public abstract class AbstractBuffer implements ProductionBuffer {
	
	private final int MAX_SIZE;
	
	//linked list of BufferSlot is the underlying implementation for this buffer
	//private LinkedList<BufferSlot> bufferll = new LinkedList<BufferSlot>();
	//This list is wrapped with a synchronized list to make it thread safe
	//private List<BufferSlot> fruitQueue = Collections.synchronizedList(bufferll);
	
	private PrintableQueue<BufferSlot> pq;
	
	public AbstractBuffer() {
		MAX_SIZE = 8;
		pq = new PrintableQueue<BufferSlot>(MAX_SIZE);
	}
	
	public AbstractBuffer(int maxSize) {
		MAX_SIZE = maxSize;
		pq = new PrintableQueue<BufferSlot>(MAX_SIZE);
	}
	
	public int getMaxBufferSize() {
		return MAX_SIZE;
	}
	
	public int getCurrentBufferSize() {
		return pq.size();
	}
	
	public synchronized boolean isFull() {
		//get current size
		if (pq.size() == MAX_SIZE) return true;
		return false;
	}
	
	public synchronized boolean isEmpty() {
		return pq.isEmpty();
	}
	
	//adds a fruit to the back of the queue
	public synchronized boolean add(BufferSlot bs) {
		if (!isFull()) {
			pq.add(bs);
			return true;
		}
		//System.out.println("Buffer is full!");
		return false;
	}
	
	//removes a fruit from the front of the queue
	//If the queue is empty an exception is raised
	public synchronized BufferSlot remove() {
		if (!isEmpty()) {
			return pq.remove();
		}
		throw new RuntimeException("trying to remove from empty Buffer!");
	}
	
	//empties the buffer
	public synchronized void clear() {
		pq.clear();
	}
	
	/**
	 * This method returns an array containing all items in
	 * the AbstractBuffer. The objective of this method is to allow
	 * the AbstractBuffer to be printed or drawn, if that is
	 * required.
	 * 
	 * @return an array of all the items in this AbstractBuffer
	 */
	public synchronized BufferSlot[] getPrintArray() {
		BufferSlot[] items = new BufferSlot[pq.size()];
		items = pq.toArray(items);
		return items;
	}
	
	//TODO Google the correct use of iterator here
	@Override
	public synchronized String toString() {
		String string = new String("Buffer size: " + pq.size() + "\n");
		Iterator<BufferSlot> i = pq.iterator(); // Must be in synchronized block
		while (i.hasNext()) {
			string += i.next().toString();
		}
		return string; 
	}
}
