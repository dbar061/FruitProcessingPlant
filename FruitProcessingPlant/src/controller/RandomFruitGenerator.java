package controller;

import inventory.fruit.Apple;
import inventory.fruit.Banana;
import inventory.fruit.Pear;

import java.io.PrintStream;
import java.net.InetSocketAddress;

import controller.NetworkConnection.MessageType;

/**
 * @file RandomFruitGeneratoin.java
 * @author				Devin Barry
 * @date 				19/01/2013
 * @lastModification 	20/01/2013
 *
 * This class randomly generates fruit every 1.2 seconds
 * and sends it over the network.
 */
public class RandomFruitGenerator implements Runnable {
	
	private InetSocketAddress serverAddress;
	private PrintStream windowOut;
	private Integer port;
	
	//these variables are used to stop and suspend the current thread
	private volatile Thread generator = null;
	private volatile boolean threadSuspended = false;
	
	public RandomFruitGenerator(InetSocketAddress serverAddress, PrintStream windowOut) {
		this.windowOut = windowOut;
		this.serverAddress = serverAddress;
		port = new Integer(serverAddress.getPort());
	}
	
	@Override
	public void run() {
		Thread thisThread = Thread.currentThread(); //allows testing for the stop condition
		while (generator == thisThread) { //test here for the stop condition
			sendFruit();
			try {
				Thread.sleep(1000);
				
				synchronized(this) {
					while (threadSuspended) {
						wait();
					}
				}
			}
			catch (InterruptedException ie) {
				System.err.println(ie);
				//We have been interrupted, terminate the thread
				return;
			}
		}
	}
	
	//Creates a new thread and invokes the run() method from it
	public void start() {
		if (generator == null) {
			generator = new Thread(this);
			generator.start();
		}
		else {
			windowOut.println("Generator is already running!");
		}
	}
	
	//Activates the thread stop condition inside run()
	public synchronized void stop() {
		generator = null;
		notify(); //in case the thread is suspended
	}
	
	//Suspends the running thread, causing it to wait on the current object
	//If already paused then this method unpauses the run thread
	public synchronized void pause() {
		threadSuspended = !threadSuspended;
		if (!threadSuspended) {
			notify();
		}
	}
	
	/**
	 * Randomly select which fruit to send
	 */
	private void sendFruit() {
		double select = Math.random();
		if (select < 0.3333) {
			Apple toSend = new Apple();
			windowOut.println("Sending: " + toSend);
			sendNetworkObject(toSend);
		}
		else if (select < 0.6666) {
			Pear toSend = new Pear();
			windowOut.println("Sending: " + toSend);
			sendNetworkObject(toSend);
		}
		else {
			Banana toSend = new Banana();
			windowOut.println("Sending: " + toSend);
			sendNetworkObject(toSend);
		}
	}
	
	private void sendNetworkObject(Object object) {
		NetworkConnection network = new NetworkConnection(serverAddress.getHostName());
		network.setMessageType(MessageType.OBJECT);
		network.setPortNumber(port.toString());
		network.setSendObject(object);
		//Start network stuff in a new thread
		Thread t = new Thread(network);
		t.start();
	}
	
	
	@SuppressWarnings("unused")
	private void sendNetworkString(String string) {
		NetworkConnection network = new NetworkConnection(serverAddress.getHostName());
		network.setMessageType(MessageType.STRING);
		network.setPortNumber(port.toString());
		network.setSendString(string);
		//Start network stuff in a new thread
		Thread t = new Thread(network);
		t.start();
	}
}
