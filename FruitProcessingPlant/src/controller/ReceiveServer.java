package controller;

import network.SimpleServerQueue;
import network.ServerQueue;
import network.ObjectReceiveServer;
import network.ObjectSocketServer;
import inventory.Inventory;


/**
 * ReceiveServer.java
 * 
 * @author			Devin Barry
 * @date			25.10.2012
 * @lastModified	07.01.2013
 * 
 * This is the main class for the various server
 * implementations.
 * 
 * This class is also used to test new server code
 * 
 */
@SuppressWarnings("unused")
public class ReceiveServer {

	/**
	 * Main method to start the receive server
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		testObjectSocketServer();
	}
	
	
	private static void testDevinsStuff() {
		SimpleServerQueue<Boolean> bq = new SimpleServerQueue<Boolean>();
		SimpleServerQueue<Integer> iq = new SimpleServerQueue<Integer>();
		//Start server
		System.out.println("Starting Main Server");
		
		//StringReceiveServer srs1 = new StringReceiveServer("55552");
		//Thread t1 = new Thread(srs1);
		
		//These call are not super elegant but they get the job done.
		ObjectReceiveServer<Boolean> ors1 = ObjectReceiveServer.createMyObject("55551", bq, Boolean.class);
		ObjectReceiveServer<Integer> ors2 = ObjectReceiveServer.createMyObject("55552", iq, Integer.class);
		
		Thread t2 = new Thread(ors1);
		Thread t3 = new Thread(ors2);
		//t1.start();
		t2.start();
		t3.start();
		
		for (int i = 0; i < 5; i++) {
			Boolean nextB = bq.get();
			System.out.println("We got a boolean of " + nextB);
			Integer nextI = iq.get();
			System.out.println("We got an Integer of " + nextI);
		}
	}
	
	/**
	 * Test the ObjectSocketServer
	 */
	private static void testObjectSocketServer() {
		ServerQueue<String> stringQ = new ServerQueue<String>();
		
		//Start server
		System.out.println("Starting ObjectSocketServer test...");
		
		//These call are not super elegant but they get the job done.
		ObjectSocketServer<String> stringOSS = ObjectSocketServer.createServer("55551", stringQ, String.class);
		
		Thread serverThread = new Thread(stringOSS);
		serverThread.start(); //this runs a while loop that never ends
		
		for (int i = 0; i < 999; i++) {
			String nextString = stringQ.get();
			System.out.println("Number: " + i + " :We got a String from server: " + nextString);
		}
	}
	
	
	
	private static void testBlocking() {
		SimpleServerQueue<Boolean> bq = new SimpleServerQueue<Boolean>();
		//Start server
		System.out.println("Starting Main Server");
		
		//StringReceiveServer srs1 = new StringReceiveServer("55552");
		//Thread t1 = new Thread(srs1);
		
		//These call are not super elegant but they get the job done.
		ObjectReceiveServer<Boolean> ors1 = ObjectReceiveServer.createMyObject("55555", bq, Boolean.class);
		
		Thread t2 = new Thread(ors1);
		//t1.start();
		t2.start();
		
		for (int i = 0; i < 5; i++) {
			Boolean nextB = bq.get();
			System.out.println("We got a boolean of " + nextB);
		}
	}
	
	
	private static void testGetInventory() {
		SimpleServerQueue<Inventory> bq = new SimpleServerQueue<Inventory>();
		//Start server
		System.out.println("Starting Main Server");
		
		//StringReceiveServer srs1 = new StringReceiveServer("55552");
		//Thread t1 = new Thread(srs1);
		
		//These call are not super elegant but they get the job done.
		ObjectReceiveServer<Inventory> ors1 = ObjectReceiveServer.createMyObject("55551", bq, Inventory.class);
		
		Thread t2 = new Thread(ors1);
		t2.start();
		
		for (;;) {
			Inventory nextB = bq.get();
			System.out.println("We got Inventory: " + nextB);
		}
	}

}
