package buffer;

import java.util.*;


/**
 * PrintableQueue.java
 * @author:			Devin Barry
 * @date:			13.10.2012
 * @lastModified:	13.10.2012
 *
 * A PrintableQueue is an implementation of the Queue Interface, but
 * it can be easily iterated, such that all items in the queue can
 * be easily printed.
 * 
 * This class could possible implement the BlockingQueue Interface instead.
 * 
 * This class has all its methods synchronized to make it thread safe. It does
 * the same that that Collections.synchronizedList(List<E>) does
 * 
 * If we wanted more thread safety we could instead use CopyOnWriteArrayList
 * from java.util.concurrent
 */

public class PrintableQueue<E> extends ArrayList<E> implements Queue<E> {
	
	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 8188502828643084182L;

	public final int MAX_SIZE;
	
	//linked list of BufferSlot is the underlying implementation for this buffer
	//private LinkedList<BufferSlot> bufferll = new LinkedList<BufferSlot>();
	//This list is wrapped with a synchronized list to make it thread safe
	//private List<BufferSlot> fruitQueue = Collections.synchronizedList(bufferll);
	
	public PrintableQueue() {
		super(8);
		MAX_SIZE = 8;
	}
	
	public PrintableQueue(int size) {
		super(size);
		MAX_SIZE = size;
	}
	
	public synchronized E element() {
		Iterator<E> i = this.iterator(); // Must be in synchronized block for thread safety
		if (i.hasNext()) {
			return i.next();
		}
		throw new NoSuchElementException("PrintableQueue is empty");
	 }
	
	public synchronized boolean offer(E e) {
		return false;
	}
	public synchronized E peek() {
		Iterator<E> i = this.iterator(); // Must be in synchronized block for thread safety
		if (i.hasNext()) {
			return i.next();
		}
		throw new NoSuchElementException("PrintableQueue is empty");
		
	}
	
	public synchronized E poll() {
		Iterator<E> i = this.iterator(); // Must be in synchronized block for thread safety
		if (i.hasNext()) {
			return i.next();
		}
		throw new NoSuchElementException("PrintableQueue is empty");		
	}
	
	//removes an item from the front of the PrintableQueue
	public synchronized E remove() {
		E removed;
		Iterator<E> i = this.iterator(); // Must be in synchronized block for thread safety
		if (i.hasNext()) {
			removed = i.next();
			i.remove();
		}
		else {
			throw new NoSuchElementException("PrintableQueue is empty");
		}
		return removed;
	}
	
	@Override
	public synchronized boolean add(E e) {
		return super.add(e);
	}
	
	//Google the correct use of iterator here
	@Override
	public synchronized String toString() {
		String string = "";
		Iterator<E> i = this.iterator();
		while (i.hasNext()) {
			string += i.next().toString();
		}
		return string; 
	}
}
