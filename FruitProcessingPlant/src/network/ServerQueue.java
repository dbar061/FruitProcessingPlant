package network;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * ServerQueue.java
 * 
 * @author			Devin Barry
 * @date			25.10.2012
 * @lastModified	31.12.2012
 * 
 * A very simple wait / notify queue implementation used to
 * pass elements of type E from different socket threads between
 * themselves. This class allows consumer threads to sleep while
 * waiting for producer threads to put an item into the queue.
 * 
 * This queue implementation is different from SimpleServeQueue
 * in that it implements a queue whose size is larger than one.
 * It is not just a single space buffer. Presently this class
 * has a default queue size of 10.
 * 
 * No explicit calls to wait and notify are needed, as these are
 * dealt with by the underlying <BlockingQueue> instance. This
 * class is thread safe for use by multiple producers and
 * consumers. Wait and Notify and called automatically by this
 * class during the <take> and <put> methods.
 * 
 * Thus <ServerQueue> is only a simple extension to <BlockingQueue>
 * catching the InterruptedException and printing an error message
 */
public class ServerQueue<E> {
	
	BlockingQueue<E> q;
	
	public ServerQueue() {
		q = new ArrayBlockingQueue<E>(10);
	}
	
	//This constructor allows the use of queues larger than 10
	public ServerQueue(int qSize) {
		if (qSize < 10) {
			q = new ArrayBlockingQueue<E>(10);
		}
		else {
			q = new ArrayBlockingQueue<E>(qSize);
		}
	}

	/**
	 * This get method gets an object from the Queue.
	 * Synchronisation is dealt with automatically
	 * @return
	 */
	public E get() {
		E e = null;
		try {
			//if it is full, the current thread automatically sleeps
			e = q.take();
		}
		catch (InterruptedException ie) {
			System.out.println("InterruptedException caught");
		}
		//System.out.println("Got: " + e);
		return e;
	}

	/**
	 * This put method puts an object into the Queue.
	 * Synchronisation is dealt with automatically
	 * @param e
	 */
	public void put(E e) {
		try {
			//if it is full, the current thread automatically sleeps
			q.put(e);
		}
		catch (InterruptedException ie) {
			System.out.println("InterruptedException caught");
		}
		//System.out.println("Put: " + e);
	}

}
