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
	
	public RandomFruitGenerator(InetSocketAddress serverAddress, PrintStream windowOut) {
		this.windowOut = windowOut;
		this.serverAddress = serverAddress;
		port = new Integer(serverAddress.getPort());
	}
	
	@Override
	public void run() {
		for (;;) {
			sendFruit();
			try {
				Thread.sleep(2000);
			}
			catch (InterruptedException ie) {
				System.err.println(ie);
			}
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
