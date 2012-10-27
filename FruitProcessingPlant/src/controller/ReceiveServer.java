package controller;

import inventory.Inventory;

public class ReceiveServer {

	/**
	 * ReceiveServer.java
	 * 
	 * @author			Devin Barry
	 * @date			25.10.2012
	 * @lastModified	28.10.2012
	 * 
	 * This is the main class for the various server
	 * implementations.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		testBlocking();
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
